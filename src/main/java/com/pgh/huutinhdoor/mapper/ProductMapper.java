package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.dto.response.ProductResponeInternal;
import com.pgh.huutinhdoor.entity.Product;
import com.pgh.huutinhdoor.dto.response.ProductResponeClient;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponeClient toClientRespone(Product p) {
        if (p == null) return null;

        return ProductResponeClient.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .stockQuantity(p.getStockQuantity())
                .unit(p.getUnit())
                .isAvailable(p.getIsAvailable())

                .categoryId(p.getCategory() != null ? p.getCategory().getId() : null)
                .categoryName(p.getCategory() != null ? p.getCategory().getName() : "Chưa phân loại")
                .build();
    }

    public ProductResponeInternal toInternalRespone(Product p) {
        if (p == null) return null;

        return ProductResponeInternal.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .costPrice(p.getCostPrice())
                .stockQuantity(p.getStockQuantity())
                .unit(p.getUnit())
                .isAvailable(p.getIsAvailable())

                .categoryId(p.getCategory() != null ? p.getCategory().getId() : null)
                .categoryName(p.getCategory() != null ? p.getCategory().getName() : "Chưa phân loại")
                .build();
    }
}