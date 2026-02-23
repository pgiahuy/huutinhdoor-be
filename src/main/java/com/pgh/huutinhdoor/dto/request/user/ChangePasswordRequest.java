package com.pgh.huutinhdoor.dto.request.user;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
