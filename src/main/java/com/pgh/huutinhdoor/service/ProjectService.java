package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.ProjectCreateRequest;
import com.pgh.huutinhdoor.entity.Category;
import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.entity.Project;
import com.pgh.huutinhdoor.entity.Ticket;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.repository.CategoryRepository;
import com.pgh.huutinhdoor.repository.CustomerRepository;
import com.pgh.huutinhdoor.repository.ProjectRepository;
import com.pgh.huutinhdoor.repository.TicketRepository;
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

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Transactional
    public Project create(ProjectCreateRequest request) {

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


        return projectRepository.save(project);
    }

}
