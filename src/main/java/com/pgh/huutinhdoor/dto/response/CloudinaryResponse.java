package com.pgh.huutinhdoor.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CloudinaryResponse {
    private String secure_url;
    private String public_id;
    private String message;
}