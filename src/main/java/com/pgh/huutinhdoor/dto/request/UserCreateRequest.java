package com.pgh.huutinhdoor.dto.request;

import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class UserCreateRequest {
    private Long id;
    @NotBlank
    private String phone;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

    private MultipartFile avatar;

    private UserRole role;

    private Boolean isActive;
}
