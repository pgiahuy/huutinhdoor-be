package com.pgh.huutinhdoor.dto.response;

import com.pgh.huutinhdoor.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String phone;
    private String email;
    private UserRole role;
    private Long customerId;
}
