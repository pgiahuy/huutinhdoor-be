package com.pgh.huutinhdoor.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseClient {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String unit;
    private Boolean isAvailable;
    private Long categoryId;
    private String categoryName;
}
