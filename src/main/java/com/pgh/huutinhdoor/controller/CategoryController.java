package com.pgh.huutinhdoor.controller;

import com.pgh.huutinhdoor.dto.request.CategoryCreateRequest;
import com.pgh.huutinhdoor.dto.response.CategoryResponse;
import com.pgh.huutinhdoor.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryCreateRequest request) {

        CategoryResponse response = categoryService.create(request);

        URI location = URI.create("/api/categories/" + response.getId());

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }
}
