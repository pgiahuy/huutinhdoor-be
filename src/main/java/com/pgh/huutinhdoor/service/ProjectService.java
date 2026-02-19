package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.ProjectCreateRequest;
import com.pgh.huutinhdoor.dto.request.ProjectUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.ProjectResponse;
import com.pgh.huutinhdoor.entity.*;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.mapper.ProjectMapper;
import com.pgh.huutinhdoor.repository.*;
import com.pgh.huutinhdoor.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final ProjectMapper projectMapper;

    public List<Project> getAll() {
        return projectRepository.findAllWithRelations();
    }

    public Project findByIdOrThrow(Long id) {
        return projectRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Project not found with id: " + id));
    }

    @Transactional
    public ProjectResponse create(ProjectCreateRequest request) {
        Project project = new Project();
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setLocation(request.getLocation());

        if (request.getCustomerId() != null) {
            Customer customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
            project.setCustomer(customer);
        }
        if (request.getTicketId() != null) {
            Ticket ticket = ticketRepository.findById(request.getTicketId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
            project.setTicket(ticket);
        }
        List<Category> categories = categoryRepository
                .findAllById(request.getCategoryIds());
        if (categories.size() != request.getCategoryIds().size()) {
            throw new ResourceNotFoundException("Some categories not found");
        }

        project.setCategories(new HashSet<>(categories));

        Project saved = projectRepository.save(project);
        return projectMapper.toAdminResponse(saved);
    }

    @Transactional
    public ProjectResponse update(Long id, ProjectUpdateRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id) );
        EntityUtil.copyNoNullProperties(request,project);
        Project saved = projectRepository.save(project);
        return projectMapper.toAdminResponse(saved);

    }

    @Transactional
    public void delete(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found with id: " + id));
        projectRepository.delete(project);
    }

}
