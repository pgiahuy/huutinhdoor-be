package com.pgh.huutinhdoor.dto.request.user;

import com.pgh.huutinhdoor.enums.UserRole;
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
