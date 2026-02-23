package com.pgh.huutinhdoor.controller.admin;

import com.pgh.huutinhdoor.dto.request.SupplierCreateRequest;
import com.pgh.huutinhdoor.dto.request.SupplierUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.SupplierResponse;
import com.pgh.huutinhdoor.entity.Image;
import com.pgh.huutinhdoor.entity.Supplier;
import com.pgh.huutinhdoor.mapper.SupplierMapper;
import com.pgh.huutinhdoor.repository.ImageRepository;
import com.pgh.huutinhdoor.service.SupplierService;
import com.pgh.huutinhdoor.util.GlobalConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/suppliers")
@RequiredArgsConstructor
public class AdminSupplierController {
    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplier(@PathVariable Long id) {
        var sw = supplierService.getWithAvatar(id);
        return ResponseEntity.ok(
                supplierMapper.toAdminResponse(sw.supplier(), sw.avatarUrl())
        );
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAll() {
        return ResponseEntity.ok(
                supplierService.getAllWithAvatar()
                        .stream()
                        .map(sw -> supplierMapper.toAdminResponse(
                                sw.supplier(),
                                sw.avatarUrl()
                        ))
                        .toList()
        );
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> createSupplier(
            @RequestBody SupplierCreateRequest request) {

        var sw = supplierService.createWithAvatar(request);
        SupplierResponse response = supplierMapper.toAdminResponse(sw.supplier(), sw.avatarUrl());
        return ResponseEntity
                .created(URI.create("/api/v1/admin/suppliers/" + response.getId()))
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> update(
            @PathVariable Long id,
            @RequestBody SupplierUpdateRequest request) {

        var sw = supplierService.update(id, request);

        return ResponseEntity.ok(
                supplierMapper.toAdminResponse(sw.supplier(), sw.avatarUrl())
        );
    }
}
