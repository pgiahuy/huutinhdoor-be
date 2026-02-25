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

    public Project toEntity(ProjectCreateRequest req) {
        return Project.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .location(req.getLocation())
                .customerName(req.getCustomerName())
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
                .completedAt(project.getCompletedAt())
                .build();
    }

    public com.pgh.huutinhdoor.dto.response.admin.ProjectResponse toAdminResponse(Project project) {
        if (project == null) {
            return null;
        }
        return com.pgh.huutinhdoor.dto.response.admin.ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .slug(project.getSlug())
                .description(project.getDescription())
                .location(project.getLocation())
                .isPublished(project.getIsPublished())
                .publishedAt(project.getPublishedAt())
                .sourceTicketId(project.getSourceTicketId())
                .viewCount(project.getViewCount())
                .completedAt(project.getCompletedAt())
                .updatedAt(project.getUpdatedAt())
                .build();
    }

}
