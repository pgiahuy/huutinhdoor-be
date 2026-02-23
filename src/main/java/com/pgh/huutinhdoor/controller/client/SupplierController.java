package com.pgh.huutinhdoor.controller.client;

import com.pgh.huutinhdoor.dto.response.client.SupplierResponse;
import com.pgh.huutinhdoor.entity.Image;
import com.pgh.huutinhdoor.entity.Supplier;
import com.pgh.huutinhdoor.mapper.SupplierMapper;
import com.pgh.huutinhdoor.service.SupplierService;
import com.pgh.huutinhdoor.util.GlobalConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplier(@PathVariable Long id) {
        var sw = supplierService.getWithAvatar(id);
        return ResponseEntity.ok(
                supplierMapper.toClientResponse(sw.supplier(), sw.avatarUrl())
        );
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAll() {
        return ResponseEntity.ok(
                supplierService.getAllWithAvatar()
                        .stream()
                        .map(sw -> supplierMapper.toClientResponse(
                                sw.supplier(),
                                sw.avatarUrl()
                        ))
                        .toList()
        );
    }
}
