package com.pgh.huutinhdoor.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloudinaryResponse {
    private String secure_url;
    private String public_id;
}