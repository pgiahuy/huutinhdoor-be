package com.pgh.huutinhdoor.dto.response.client;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectClientResponse {


    private Long id;

    private String title;
    private String description;
    private String location;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate completionDate;

    private List<Long> categoryIds;
}
