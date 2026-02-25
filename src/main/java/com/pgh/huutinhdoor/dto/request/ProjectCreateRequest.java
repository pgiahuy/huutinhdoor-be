package com.pgh.huutinhdoor.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateRequest {

    @NotBlank
    private String title;

    private String description;

    private MultipartFile thumbnail;
    private List<MultipartFile> images;

    private String location;
    private String customerName;
}