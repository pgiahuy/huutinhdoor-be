package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.dto.request.ProductCreateRequest;
import com.pgh.huutinhdoor.dto.response.admin.ProductAdminResponse;
import com.pgh.huutinhdoor.entity.Category;
import com.pgh.huutinhdoor.entity.Product;
import com.pgh.huutinhdoor.dto.response.client.ProductClientResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductCreateRequest req, Category category) {

        return Product.builder()
                .name(req.getName())
                .description(req.getDescription())
                .price(req.getPrice())
                .costPrice(req.getCostPrice())
                .stockQuantity(req.getStockQuantity())
                .isAvailable(true)
                .unit(req.getUnit())
                .category(category)
                .build();
    }

    public ProductClientResponse toClientResponse(Product p) {
        if (p == null) return null;

        return ProductClientResponse.builder()
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

    public ProductAdminResponse toAdminResponse(Product p) {
        if (p == null) return null;

        return ProductAdminResponse.builder()
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