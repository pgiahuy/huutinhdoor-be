package com.pgh.huutinhdoor.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierUpdateRequest {
    private String name;
    private String phone;
    private String address;
    private String email;
    private String note;
}
