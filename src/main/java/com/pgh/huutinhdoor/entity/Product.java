package com.pgh.huutinhdoor.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;


    private Integer stock;

    private Boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
