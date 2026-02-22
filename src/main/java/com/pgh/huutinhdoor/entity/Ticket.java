package com.pgh.huutinhdoor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pgh.huutinhdoor.enums.TicketStatus;
import com.pgh.huutinhdoor.enums.PaymentStatus;
import com.pgh.huutinhdoor.enums.TicketType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private BigDecimal totalAmount;
    private BigDecimal  deposit;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    private String paymentStatusNote;

    @Enumerated(EnumType.STRING)
    private TicketStatus workStatus;

    @Enumerated(EnumType.STRING)
    private TicketType type = TicketType.PRODUCT;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TicketItem> items = new ArrayList<>();

    public void addItem(TicketItem item) {
        items.add(item);
        item.setTicket(this);
    }

    public void removeItem(TicketItem item) {
        items.remove(item);
        item.setTicket(null);
    }

}