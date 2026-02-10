package com.pgh.huutinhdoor.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TicketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String nameAtTime;
    private Double priceAtTime;
    private Integer quantity;
}
