package com.pgh.huutinhdoor.dto.response.admin;

import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;

public class UserResponse {
    private Long id;
    private String phone;
    private String email;
    private UserRole role;
    private Long customerId;
}
