package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.ProductCreateRequest;
import com.pgh.huutinhdoor.dto.request.ProductUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.ProductResponse;
import com.pgh.huutinhdoor.entity.Category;
import com.pgh.huutinhdoor.entity.Product;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.mapper.ProductMapper;
import com.pgh.huutinhdoor.repository.CategoryRepository;
import com.pgh.huutinhdoor.repository.ProductRepository;
import com.pgh.huutinhdoor.util.EntityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public List<Product> getAll() {
        return productRepository.findAllWithCategory();
    }

    public List<Product> getByCategoryId(Long categoryId) {
        return productRepository.findAllByCategory_Id(categoryId);
    }

    public Product findByIdOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Transactional
    public ProductResponse create(ProductCreateRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Product product = productMapper.toEntity(request, category);
        Product saved = productRepository.save(product);
        return productMapper.toAdminResponse(saved);
    }

    @Transactional
    public ProductResponse update(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id) );
        EntityUtil.copyNoNullProperties(request,product);
        Product saved = productRepository.save(product);
        return productMapper.toAdminResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}
