package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.dto.request.CategoryCreateRequest;
import com.pgh.huutinhdoor.dto.response.CategoryResponse;
import com.pgh.huutinhdoor.entity.Category;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryCreateRequest request, Category parent) {
        Category category = new Category();
        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setType(request.getType());
        category.setParent(parent);
        return category;
    }

    public CategoryResponse toResponse(Category category) {
        CategoryResponse resp = new CategoryResponse();
        resp.setId(category.getId());
        resp.setName(category.getName());
        resp.setSlug(category.getSlug());
        resp.setType(category.getType());
        resp.setParentId(category.getParent() != null ? category.getParent().getId() : null);

        if (category.getSubCategories() != null) {
            resp.setSubCategories(category.getSubCategories()
                    .stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList()));
        }
        return resp;
    }


}
