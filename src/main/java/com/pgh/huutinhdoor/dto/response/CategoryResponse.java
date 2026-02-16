package com.pgh.huutinhdoor.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pgh.huutinhdoor.enums.CategoryType;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({ "id", "name", "slug", "type", "parentId", "subCategories" })
public class CategoryResponse {
    private Long id;
    private String name;
    private String slug;
    private CategoryType type;

    private Long parentId;
    private List<CategoryResponse> subCategories;
}
