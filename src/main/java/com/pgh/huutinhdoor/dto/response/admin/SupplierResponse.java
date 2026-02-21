package com.pgh.huutinhdoor.dto.response.admin;

import com.pgh.huutinhdoor.entity.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String avatar;
    private String note;
}
