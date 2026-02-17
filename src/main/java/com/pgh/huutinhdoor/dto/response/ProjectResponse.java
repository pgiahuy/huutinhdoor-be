package com.pgh.huutinhdoor.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import java.util.List;

@Data
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
