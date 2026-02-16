package com.pgh.huutinhdoor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductUpdateRequest {
    private String name;
    private String description;
    private String unit;
    private Integer stockQuantity;
    @Positive
    private Double price;
    private Double costPrice;
    private Long categoryId;
}
