package com.pgh.huutinhdoor.controller.client;

import com.pgh.huutinhdoor.dto.response.client.ProductClientResponse;
import com.pgh.huutinhdoor.entity.Product;
import com.pgh.huutinhdoor.mapper.ProductMapper;
import com.pgh.huutinhdoor.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductClientResponse>> getAllProducts() {
        List<ProductClientResponse> result = productService.getAll()
                .stream()
                .map(productMapper::toClientResponse)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductClientResponse> getById(@PathVariable Long id) {
        Product product = productService.findByIdOrThrow(id);
        return ResponseEntity.ok(productMapper.toClientResponse(product));
    }



}