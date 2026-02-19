package com.pgh.huutinhdoor.controller.admin;

import com.pgh.huutinhdoor.dto.request.ProductCreateRequest;
import com.pgh.huutinhdoor.dto.request.ProductUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.ProductResponse;
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
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> result = productService.getAll()
                .stream()
                .map(productMapper::toAdminResponse)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Product product = productService.findByIdOrThrow(id);
        return ResponseEntity.ok(productMapper.toAdminResponse(product));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getByCategoryId(@PathVariable Long categoryId) {
        List<ProductResponse> result = productService.getByCategoryId(categoryId)
                .stream()
                .map(productMapper::toAdminResponse)
                .toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductCreateRequest request) {

        ProductResponse response = productService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/admin/products" + response.getId()))
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
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
