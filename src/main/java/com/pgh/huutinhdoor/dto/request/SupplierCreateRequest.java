package com.pgh.huutinhdoor.dto.request;

import com.pgh.huutinhdoor.entity.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    private MultipartFile avatar;

    private String address;
    private String email;
    private String note;

}
