package com.pgh.huutinhdoor.controller.admin;

import com.pgh.huutinhdoor.dto.request.ProjectCreateRequest;
import com.pgh.huutinhdoor.dto.request.ProjectUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.ProjectResponse;
import com.pgh.huutinhdoor.entity.Project;
import com.pgh.huutinhdoor.mapper.ProjectMapper;
import com.pgh.huutinhdoor.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/projects")
@RequiredArgsConstructor
public class AdminProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PostMapping("/publish-from-ticket")
    public ResponseEntity<ProjectResponse> publishFromTicket(
            @RequestParam Long ticketId,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile thumbnail,
            @RequestParam(required = false) List<MultipartFile> images
    ) {

        ProjectCreateRequest request = ProjectCreateRequest.builder()
                .description(description)
                .thumbnail(thumbnail)
                .images(images)
                .build();

        Project project = projectService.publishFromTicket(ticketId, request);
        return ResponseEntity.created(URI.create("/api/v1/admin/projects/" + project.getId()))
                .body(projectMapper.toAdminResponse(project));
    }

}
