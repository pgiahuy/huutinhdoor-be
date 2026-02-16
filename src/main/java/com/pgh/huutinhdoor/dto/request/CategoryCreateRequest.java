package com.pgh.huutinhdoor.dto.request;

import com.pgh.huutinhdoor.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryCreateRequest {
    @NotBlank
    private String name;

    private String slug;

    @NotNull
    private CategoryType type;

    private Long parentId;
}
