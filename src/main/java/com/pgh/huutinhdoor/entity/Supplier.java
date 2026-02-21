package com.pgh.huutinhdoor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String phone;

    private String address;

    @Email
    private String email;

    private String avatar;

    @Column(columnDefinition = "TEXT")
    private String note;

    @OneToMany(mappedBy = "supplier")
    private List<Transaction> transactions;
}