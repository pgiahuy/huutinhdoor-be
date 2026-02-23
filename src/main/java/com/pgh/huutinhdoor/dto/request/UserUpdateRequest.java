package com.pgh.huutinhdoor.dto.request;

import com.pgh.huutinhdoor.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private Long id;
    private String phone;
    private String password;
    private String email;
    private UserRole role;
    private Boolean isActive;
    private MultipartFile avatar;
    private Long customerId;
}
