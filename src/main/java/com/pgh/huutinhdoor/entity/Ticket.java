package com.pgh.huutinhdoor.entity;

import com.pgh.huutinhdoor.enums.TicketStatus;
import com.pgh.huutinhdoor.enums.PaymentStatus;
import com.pgh.huutinhdoor.enums.TicketType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String address;

    private LocalDateTime deadline;
    private Double totalAmount;
    private Double deposit;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    private String paymentStatusNote;

    @Enumerated(EnumType.STRING)
    private TicketStatus workStatus;

    @Enumerated(EnumType.STRING)
    private TicketType type = TicketType.PRODUCT;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketItem> items = new ArrayList<>();

}