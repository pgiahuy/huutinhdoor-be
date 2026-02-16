package com.pgh.huutinhdoor.controller;

import com.pgh.huutinhdoor.dto.request.ProductCreateRequest;
import com.pgh.huutinhdoor.dto.response.ProductResponseClient;
import com.pgh.huutinhdoor.dto.response.ProductResponseInternal;
import com.pgh.huutinhdoor.entity.Product;
import com.pgh.huutinhdoor.mapper.ProductMapper;
import com.pgh.huutinhdoor.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseClient>> getAllProducts() {
        List<ProductResponseClient> result = productService.getAllProducts()
                .stream()
                .map(productMapper::toClientResponse)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductResponseClient> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(productMapper.toClientResponse(product));
    }

    @PostMapping("/admin/products")
    public ResponseEntity<ProductResponseInternal> createProduct(@Valid @RequestBody ProductCreateRequest p) {

        ProductResponseInternal response = productService.create(p);

        return ResponseEntity
                .created(URI.create("/api/products/" + response.getId()))
                .body(response);

    }

    @PutMapping("/admin/products/{id}")
    public ResponseEntity<ProductResponseClient> updateProduct(@RequestBody Product product) {
        return null;
    }
    @DeleteMapping("products/{id}")
    public ResponseEntity<ProductResponseClient> deleteProduct(@RequestBody Product product) {
        return null;
    }

}