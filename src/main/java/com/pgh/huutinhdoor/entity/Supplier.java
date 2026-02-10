package com.pgh.huutinhdoor.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String phone;

    private String address;
    private String email;

    @Column(columnDefinition = "TEXT")
    private String note;

    @OneToMany(mappedBy = "supplier")
    private List<Transaction> transactions;
}