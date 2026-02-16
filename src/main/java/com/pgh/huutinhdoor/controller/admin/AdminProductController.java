package com.pgh.huutinhdoor.controller.admin;

import com.pgh.huutinhdoor.dto.request.ProductCreateRequest;
import com.pgh.huutinhdoor.dto.request.ProductUpdateRequest;
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
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponseInternal>> getAllProducts() {
        List<ProductResponseInternal> result = productService.getAll()
                .stream()
                .map(productMapper::toInternalResponse)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseInternal> getProductById(@PathVariable Long id) {
        Product product = productService.findByIdOrThrow(id);
        return ResponseEntity.ok(productMapper.toInternalResponse(product));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseInternal>> getByCategoryId(@PathVariable Long categoryId) {
        List<ProductResponseInternal> result = productService.getByCategoryId(categoryId)
                .stream()
                .map(productMapper::toInternalResponse)
                .toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ProductResponseInternal> createProduct(@Valid @RequestBody ProductCreateRequest p) {

        ProductResponseInternal response = productService.create(p);
        return ResponseEntity
                .created(URI.create("/api/v1/admin/products" + response.getId()))
                .body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseInternal> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequest request) {
        return ResponseEntity.ok(productService.update(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
