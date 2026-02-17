package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.dto.response.ProjectResponse;
import com.pgh.huutinhdoor.entity.Category;
import com.pgh.huutinhdoor.entity.Project;

public class ProjectMapper {
    public ProjectResponse toResponse(Project project) {
        ProjectResponse response = new ProjectResponse();

        response.setId(project.getId());
        response.setTitle(project.getTitle());
        response.setDescription(project.getDescription());
        response.setLocation(project.getLocation());
        response.setCompletionDate(project.getCompletionDate());

        if (project.getCustomer() != null) {
            response.setCustomerId(project.getCustomer().getId());
        }

        if (project.getTicket() != null) {
            response.setTicketId(project.getTicket().getId());
        }

        response.setCategoryIds(
                project.getCategories()
                        .stream()
                        .map(Category::getId)
                        .toList()
        );

        return response;
    }

}
