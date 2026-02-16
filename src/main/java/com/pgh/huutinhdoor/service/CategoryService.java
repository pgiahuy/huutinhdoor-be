package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.CategoryCreateRequest;
import com.pgh.huutinhdoor.dto.response.CategoryResponse;
import com.pgh.huutinhdoor.entity.Category;
import com.pgh.huutinhdoor.mapper.CategoryMapper;
import com.pgh.huutinhdoor.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public CategoryResponse create(CategoryCreateRequest request) {
        Category parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
        }

        Category category = categoryMapper.toEntity(request, parent);
        Category saved = categoryRepository.save(category);

        return categoryMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public CategoryResponse get(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toResponse(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }
}

