package com.pgh.huutinhdoor.dto.response;

import com.pgh.huutinhdoor.enums.UserRole;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String phone;
    private String email;
    private UserRole role;
    private String avatar;
    private Long customerId;
}
