package com.pgh.huutinhdoor.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String phone;

    private String address;

    @Column(columnDefinition = "TEXT")
    private String note;

    private String type;

    @OneToMany(mappedBy = "customer")
    private List<Ticket> tickets;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}