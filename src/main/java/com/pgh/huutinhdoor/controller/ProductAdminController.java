package com.pgh.huutinhdoor.controller;

import com.pgh.huutinhdoor.dto.response.ProductResponseInternal;
import com.pgh.huutinhdoor.mapper.ProductMapper;
import com.pgh.huutinhdoor.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponseInternal>> getAllForAdmin() {
        List<ProductResponseInternal> result = productService.getAllProducts()
                .stream()
                .map(productMapper::toInternalResponse)
                .toList();
        return ResponseEntity.ok(result);
    }
}
