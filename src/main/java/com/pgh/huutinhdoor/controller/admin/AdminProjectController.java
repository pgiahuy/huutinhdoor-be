package com.pgh.huutinhdoor.controller.admin;

import com.pgh.huutinhdoor.dto.request.ProjectCreateRequest;
import com.pgh.huutinhdoor.dto.request.ProjectUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.ProjectResponse;
import com.pgh.huutinhdoor.entity.Project;
import com.pgh.huutinhdoor.mapper.ProjectMapper;
import com.pgh.huutinhdoor.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/publish-from-ticket", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectResponse> publishFromTicket(
            @RequestParam Long ticketId,
            @ModelAttribute ProjectCreateRequest request
    ) {

        Project project = projectService.publishFromTicket(ticketId, request);
        return ResponseEntity.created(URI.create("/api/v1/admin/projects/" + project.getId()))
                .body(projectMapper.toAdminResponse(project));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id,
            @ModelAttribute ProjectUpdateRequest request
    ){
        var proj = projectService.updateProject(id, request);

        return ResponseEntity.ok(
                projectMapper.toAdminResponse(proj)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
//    private String title;
//    private String description;
//
//    private Boolean isPublished;
//
//    private MultipartFile thumbnail;
//    private List<MultipartFile> images;
//
//    private List<Long> imageIdsToRemove;
//
//    private String location;
//    private String customerName;

}
