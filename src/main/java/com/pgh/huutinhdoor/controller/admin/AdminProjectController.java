package com.pgh.huutinhdoor.controller.admin;

import com.pgh.huutinhdoor.dto.request.ProductUpdateRequest;
import com.pgh.huutinhdoor.dto.request.ProjectCreateRequest;
import com.pgh.huutinhdoor.dto.request.ProjectUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.ProjectAdminResponse;
import com.pgh.huutinhdoor.entity.Project;
import com.pgh.huutinhdoor.mapper.ProjectMapper;
import com.pgh.huutinhdoor.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/projects")
@RequiredArgsConstructor
public class AdminProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public ResponseEntity<List<ProjectAdminResponse>> getAll(){
        List<ProjectAdminResponse> result = projectService.getAll().stream()
                .map(projectMapper::toAdminResponse)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectAdminResponse> getById(@PathVariable Long id){
        Project project = projectService.findByIdOrThrow(id);
        return ResponseEntity.ok(projectMapper.toAdminResponse(project));
    }

    @PostMapping
    public ResponseEntity<ProjectAdminResponse> create(@Valid @RequestBody ProjectCreateRequest request){
        ProjectAdminResponse response = projectService.create(request);
        return  ResponseEntity
                .created(URI.create("/api/v1/admin/projects"+response.getId()))
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectAdminResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProjectUpdateRequest request){
        return ResponseEntity.ok(projectService.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectAdminResponse> delete(
            @PathVariable Long id,
            @Valid @RequestBody ProjectCreateRequest request){
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
