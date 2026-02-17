package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.dto.request.ProjectCreateRequest;
import com.pgh.huutinhdoor.dto.response.admin.ProjectAdminResponse;
import com.pgh.huutinhdoor.dto.response.client.ProductClientResponse;
import com.pgh.huutinhdoor.dto.response.client.ProjectClientResponse;
import com.pgh.huutinhdoor.entity.Category;
import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.entity.Project;
import com.pgh.huutinhdoor.entity.Ticket;

import java.util.List;
import java.util.Set;

public class ProjectMapper {

    public Project toEntity(ProjectCreateRequest req, Customer customer,
                            Ticket ticket, Set<Category> categories) {
        return Project.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .location(req.getLocation())
                .customer(customer)
                .ticket(ticket)
                .categories(categories)
                .build();
    }

    public ProjectClientResponse toClientResponse(Project project) {
        if (project == null) {
            return null;
        }
        return ProjectClientResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .location(project.getLocation())
                .completionDate(project.getCompletionDate())
                .categoryIds(project.getCategories() != null ?
                        project.getCategories().stream().map(Category::getId).toList() : null)
                .build();
    }

    public ProjectAdminResponse toAdminResponse(Project project) {
        if (project == null) {
            return null;
        }
        return ProjectAdminResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .location(project.getLocation())
                .completionDate(project.getCompletionDate())
                .customerId(project.getCustomer() != null ? project.getCustomer().getId() : null)
                .ticketId(project.getTicket() != null ? project.getTicket().getId() : null)
                .categoryIds(project.getCategories() != null ?
                        project.getCategories().stream().map(Category::getId).toList() : null)
                .build();
    }

}
