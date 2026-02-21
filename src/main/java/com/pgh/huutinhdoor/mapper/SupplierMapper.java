package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.dto.request.SupplierCreateRequest;
import com.pgh.huutinhdoor.dto.response.admin.SupplierResponse;
import com.pgh.huutinhdoor.entity.Image;
import com.pgh.huutinhdoor.entity.Supplier;
import com.pgh.huutinhdoor.util.GlobalConstants;

public class SupplierMapper {

    public Supplier toEntity(SupplierCreateRequest request) {
        return Supplier.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .email(request.getEmail())
                .note(request.getNote())
                .build();
    }

    public SupplierResponse toAdminResponse(Supplier supplier, String avt) {

        return SupplierResponse.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .address(supplier.getAddress())
                .avatar(avt)
                .note(supplier.getNote())
                .build();
    }
    public com.pgh.huutinhdoor.dto.response.client.SupplierResponse toClientResponse(Supplier supplier, String avt) {

        return com.pgh.huutinhdoor.dto.response.client.SupplierResponse.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .avatar(avt )
                .build();
    }
}
