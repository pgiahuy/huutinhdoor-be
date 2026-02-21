package com.pgh.huutinhdoor.controller.admin;

import com.pgh.huutinhdoor.dto.request.SupplierCreateRequest;
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
        Supplier supplier = supplierService.findByIdOrThrow(id);
        Image avatar = supplierService.getPrimaryAvatar(id).orElse(null);

        String avatarUrl = (avatar != null && avatar.getUrl() != null)
                ? avatar.getUrl()
                : GlobalConstants.SUPPLIER_AVATAR;

        SupplierResponse response = supplierMapper.toAdminResponse(supplier, avatarUrl);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers() {

        List<Supplier> suppliers = supplierService.getAll();
        List<SupplierResponse> result = suppliers.stream()
                .map(supplier -> {
                    Image avatar = supplierService.getPrimaryAvatar(supplier.getId()).orElse(null);
                    String avatarUrl = (avatar != null && avatar.getUrl() != null)
                            ? avatar.getUrl()
                            : GlobalConstants.SUPPLIER_AVATAR;
                    return supplierMapper.toAdminResponse(supplier, avatarUrl);
                })
                .toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> createSupplier(@RequestBody SupplierCreateRequest request) {
        Supplier supplier = supplierService.create(request);
        Image avatar = supplierService.getPrimaryAvatar(supplier.getId()).orElse(null);

        String avatarUrl = (avatar != null && avatar.getUrl() != null)
                ? avatar.getUrl()
                : GlobalConstants.SUPPLIER_AVATAR;
        SupplierResponse response = supplierMapper.toAdminResponse(supplier, avatarUrl);

        return ResponseEntity
                .created(URI.create("/api/v1/admin/suppliers"+response.getId()) )
                .body(response);
    }
}
