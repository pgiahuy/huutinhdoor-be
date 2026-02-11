package com.pgh.huutinhdoor.controller;

import com.pgh.huutinhdoor.dto.response.ProductResponeClient;
import com.pgh.huutinhdoor.entity.Product;
import com.pgh.huutinhdoor.mapper.ProductMapper;
import com.pgh.huutinhdoor.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponeClient>> getAll() {
        List<ProductResponeClient> result = productService.getAllProducts()
                .stream()
                .map(productMapper::toClientRespone)
                .toList();
        return ResponseEntity.ok(result);
    }
}