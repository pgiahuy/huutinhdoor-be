package com.pgh.huutinhdoor.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientUserUpdateRequest {
    private String phone;
    private String email;
    private MultipartFile avatar;
}
