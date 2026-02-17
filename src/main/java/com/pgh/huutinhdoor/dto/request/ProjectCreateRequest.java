package com.pgh.huutinhdoor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateRequest {
    @NotBlank
    private String title;
    private String description;
    private String location;
    private Long customerId;
    private Long ticketId;

    @NotEmpty
    private List<Long> categoryIds;

}
