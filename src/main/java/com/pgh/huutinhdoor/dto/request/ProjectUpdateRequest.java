package com.pgh.huutinhdoor.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdateRequest {

    private String title;
    private String description;

    private Boolean isPublished;

    private MultipartFile thumbnail;
    private List<MultipartFile> images;

    private List<Long> imageIdsToRemove;

    private String location;
    private String customerName;
}