package com.pgh.huutinhdoor.dto.request.user;

import com.pgh.huutinhdoor.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @Pattern(regexp = "^[0-9]{10}$", message = "Số điện thoại là 10 chữ số")
    private String phone;

    @Size(min = 6, message = "Mật khẩu có ít nhất 6 kí tự")
    private String password;

    @Email(message = "Email không hợp lệ")
    private String email;

    private MultipartFile avatar;
}