package com.pgh.huutinhdoor.entity;

import com.pgh.huutinhdoor.enums.TicketStatus;
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

    @Column(unique = true)
    private String code;
    private String title;

    private String customerName;
    private String customerPhone;

    private LocalDateTime deadline;
    private Double totalAmount;
    private Double deposit;
    private Boolean isPaid = false;

    @Enumerated(EnumType.STRING)
    private TicketStatus workStatus;

    private String type;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<TicketItem> items = new ArrayList<>();
}