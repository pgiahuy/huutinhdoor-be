package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.dto.request.ProjectCreateRequest;
import com.pgh.huutinhdoor.dto.response.client.ProjectResponse;
import com.pgh.huutinhdoor.entity.Category;
import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.entity.Project;
import com.pgh.huutinhdoor.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectCreateRequest req,
                            Ticket ticket, Set<Category> categories) {
        return Project.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .location(req.getLocation())
                .ticket(ticket)
                .categories(categories)
                .build();
    }

    public ProjectResponse toClientResponse(Project project) {
        if (project == null) {
            return null;
        }
        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .location(project.getLocation())
                .completionDate(project.getCompletionDate())
                .categoryIds(project.getCategories() != null ?
                        project.getCategories().stream().map(Category::getId).toList() : null)
                .build();
    }

    public com.pgh.huutinhdoor.dto.response.admin.ProjectResponse toAdminResponse(Project project) {
        if (project == null) {
            return null;
        }
        return com.pgh.huutinhdoor.dto.response.admin.ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .location(project.getLocation())
                .completionDate(project.getCompletionDate())
                .ticketId(project.getTicket() != null ? project.getTicket().getId() : null)
                .categoryIds(project.getCategories() != null ?
                        project.getCategories().stream().map(Category::getId).toList() : null)
                .build();
    }

}
