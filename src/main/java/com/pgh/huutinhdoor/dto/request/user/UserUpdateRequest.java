package com.pgh.huutinhdoor.dto.request.user;

import com.pgh.huutinhdoor.enums.UserRole;
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
    private String phone;
    private String password;
    private String email;
    private UserRole role;
    private Boolean isActive;
    private MultipartFile avatar;
}
