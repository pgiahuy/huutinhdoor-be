package com.pgh.huutinhdoor.dto.response.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private Long userId;
}
