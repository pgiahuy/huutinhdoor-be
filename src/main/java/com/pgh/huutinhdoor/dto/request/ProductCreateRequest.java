package com.pgh.huutinhdoor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductCreateRequest {

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String unit;

    @NotNull
    private Integer stockQuantity;



    @NotNull
    @Positive
    private Double price;
    private Double costPrice;

    @NotNull
    private Long categoryId;
}
