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

    private Long productId;

    private String nameAtTime;
    private Double priceAtTime;
    private Integer quantity;
}
