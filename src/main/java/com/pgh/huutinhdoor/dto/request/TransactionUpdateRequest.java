package com.pgh.huutinhdoor.dto.request;

import com.pgh.huutinhdoor.enums.PaymentType;
import com.pgh.huutinhdoor.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionUpdateRequest {

    private String title;
    private String description;
    private BigDecimal amount;
    private TransactionType transactionType;
    private PaymentType paymentMethod;

    private Long referenceId;
    private String referenceType;

    private Long categoryId;
    private Long customerId;
    private Long supplierId;
    private LocalDateTime transactionDate;
    private String createdBy;
}
