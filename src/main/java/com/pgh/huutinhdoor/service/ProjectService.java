package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.ProjectCreateRequest;
import com.pgh.huutinhdoor.dto.request.ProjectUpdateRequest;
import com.pgh.huutinhdoor.entity.Project;
import com.pgh.huutinhdoor.entity.Ticket;
import com.pgh.huutinhdoor.enums.TargetType;
import com.pgh.huutinhdoor.enums.TicketStatus;
import com.pgh.huutinhdoor.enums.UploadFolder;
import com.pgh.huutinhdoor.exception.BadRequestException;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.repository.ProjectRepository;
import com.pgh.huutinhdoor.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TicketRepository ticketRepository;
    private final ImageService imageService;

    @Transactional
    public Project publishFromTicket(Long ticketId, ProjectCreateRequest request) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + ticketId));

        if (ticket.getWorkStatus() != TicketStatus.COMPLETED) {
            throw new BadRequestException("Ticket must be completed before publishing");
        }

        Project project = new Project();
        project.setTitle(ticket.getTitle());
        project.setDescription(request.getDescription());
        project.setPublishedAt(LocalDateTime.now());
        project.setIsPublished(true);

        Project savedProject = projectRepository.save(project);

        handleThumbnail(savedProject, request.getThumbnail());
        handleGalleryImages(savedProject, request.getImages());

        return savedProject;
    }

    @Transactional
    public Project updateProject(Long projectId, ProjectUpdateRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        if (request.getTitle() != null) project.setTitle(request.getTitle());
        if (request.getDescription() != null) project.setDescription(request.getDescription());
        if (request.getIsPublished() != null) project.setIsPublished(request.getIsPublished());

        handleThumbnail(project, request.getThumbnail());

        if (request.getImageIdsToRemove() != null) {
            request.getImageIdsToRemove().forEach(id ->
                    imageService.deleteById(id)
            );
        }
        handleGalleryImages(project, request.getImages());
        return projectRepository.save(project);
    }


    private void handleThumbnail(Project project, MultipartFile thumbnail) {
        if (thumbnail != null && !thumbnail.isEmpty()) {
            imageService.replacePrimaryImage(
                    project.getId(),
                    TargetType.PROJECT,
                    thumbnail,
                    UploadFolder.PROJECT
            );
        }
    }

    private void handleGalleryImages(Project project, List<MultipartFile> images) {
        if (images != null && !images.isEmpty()) {
            images.forEach(file ->
                    imageService.uploadAdditionalImage(
                            project.getId(),
                            TargetType.PROJECT,
                            file,
                            UploadFolder.PROJECT
                    )
            );
        }
    }
}