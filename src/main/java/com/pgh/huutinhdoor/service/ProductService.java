package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.ProductCreateRequest;
import com.pgh.huutinhdoor.dto.response.ProductResponseInternal;
import com.pgh.huutinhdoor.entity.Category;
import com.pgh.huutinhdoor.entity.Product;
import com.pgh.huutinhdoor.mapper.ProductMapper;
import com.pgh.huutinhdoor.repository.CategoryRepository;
import com.pgh.huutinhdoor.repository.ProductRepository;
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

    public List<Product> getAllProducts() {
        return productRepository.findAllWithCategory();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional
    public ProductResponseInternal create(ProductCreateRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = productMapper.toEntity(request, category);

        Product saved = productRepository.save(product);
        return productMapper.toInternalResponse(saved);
    }
}
