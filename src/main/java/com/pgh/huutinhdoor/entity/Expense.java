package com.pgh.huutinhdoor.entity;

import com.pgh.huutinhdoor.enums.ExpenseType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double amount;

    private LocalDateTime expenseDate;

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    private String note;
}