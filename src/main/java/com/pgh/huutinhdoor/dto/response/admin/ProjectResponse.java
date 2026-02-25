package com.pgh.huutinhdoor.dto.response.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private Long id;
    private String title;
    private String slug;
    private String description;
    private String location;
    private Boolean isPublished;
    private LocalDateTime publishedAt;
    private Long sourceTicketId;
    private Integer viewCount;
    private LocalDateTime completedAt;
    private LocalDateTime updatedAt;
}
