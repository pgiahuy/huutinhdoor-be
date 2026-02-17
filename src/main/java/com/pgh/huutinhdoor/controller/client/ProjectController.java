package com.pgh.huutinhdoor.controller.client;

import com.pgh.huutinhdoor.dto.response.client.ProjectClientResponse;
import com.pgh.huutinhdoor.entity.Project;
import com.pgh.huutinhdoor.mapper.ProjectMapper;
import com.pgh.huutinhdoor.repository.ProjectRepository;
import com.pgh.huutinhdoor.service.CloudinaryService;
import com.pgh.huutinhdoor.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public ResponseEntity<List<ProjectClientResponse>> getAll() {
        List<ProjectClientResponse> result = projectService.getAll().stream()
                .map(projectMapper::toClientResponse).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectClientResponse> getById(@PathVariable Long id) {
        Project project = projectService.findByIdOrThrow(id);
        return ResponseEntity.ok(projectMapper.toClientResponse(project));
    }




}
