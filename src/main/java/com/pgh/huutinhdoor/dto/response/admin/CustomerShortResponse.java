package com.pgh.huutinhdoor.dto.response.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerShortResponse {
    private Long id;
    private String name;
    private String phone;
}
