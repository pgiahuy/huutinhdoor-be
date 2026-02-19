package com.pgh.huutinhdoor.dto.response.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private Long id;

    private String title;
    private String description;
    private String location;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate completionDate;

    private Long customerId;

    private Long ticketId;

    private List<Long> categoryIds;
}
