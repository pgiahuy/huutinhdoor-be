package com.pgh.huutinhdoor.mapper;


import com.pgh.huutinhdoor.dto.request.TransactionCreateRequest;
import com.pgh.huutinhdoor.dto.response.admin.TransactionResponse;
import com.pgh.huutinhdoor.entity.Category;
import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.entity.Supplier;
import com.pgh.huutinhdoor.entity.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapper {
    public TransactionResponse toResponse(Transaction transaction){
        return TransactionResponse.builder()
                .id(transaction.getId())
                .title(transaction.getTitle())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .transactionType(transaction.getType())
                .paymentMethod(transaction.getPaymentMethod())
                .referenceId(transaction.getReferenceId())
                .referenceType(transaction.getReferenceType())
                .categoryId(transaction.getCategory().getId())
                .customerId(transaction.getCustomer().getId())
                .supplierId(transaction.getSupplier().getId())
                .transactionDate(transaction.getTransactionDate())
                .createdBy(transaction.getCreatedBy())
                .build();
    }
    public Transaction toEntity(TransactionCreateRequest request, Category category,
                                Customer customer, Supplier supplier){
        return Transaction.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .amount(request.getAmount())
                .type(request.getTransactionType())
                .paymentMethod(request.getPaymentMethod())
                .referenceId(request.getReferenceId())
                .referenceType(request.getReferenceType())
                .category(category)
                .customer(customer)
                .supplier(supplier)
                .transactionDate(LocalDateTime.now())
                .build();
    }
}
