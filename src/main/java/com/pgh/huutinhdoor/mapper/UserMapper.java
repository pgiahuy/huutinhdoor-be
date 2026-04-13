package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.dto.request.user.UserCreateRequest;
import com.pgh.huutinhdoor.dto.response.UserResponse;
import com.pgh.huutinhdoor.entity.User;
import com.pgh.huutinhdoor.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user, String avatarUrl) {
        return UserResponse.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .customerId(
                        user.getCustomer() != null ? user.getCustomer().getId() : null
                )
                .build();
    }

    public UserResponse toResponseAvatar(UserResponse user, String avatarUrl) {
        return UserResponse.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .customerId(
                        user.getCustomerId() != null ? user.getCustomerId() : null
                )
                .build();
    }

    public User toEntity(UserCreateRequest request) {
        return User.builder()
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
    }
}
