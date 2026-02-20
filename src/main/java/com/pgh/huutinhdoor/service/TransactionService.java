package com.pgh.huutinhdoor.service;


import com.pgh.huutinhdoor.dto.request.TransactionCreateRequest;
import com.pgh.huutinhdoor.dto.request.TransactionUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.TransactionResponse;
import com.pgh.huutinhdoor.entity.*;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.mapper.TransactionMapper;
import com.pgh.huutinhdoor.repository.*;
import com.pgh.huutinhdoor.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final SupplierRepository  supplierRepository;
    private final TransactionMapper transactionMapper;

    @Transactional(readOnly = true)
    public List<TransactionResponse> getAll() {
        return transactionRepository.findAll().stream().map(transactionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TransactionResponse findByIdOrThrow(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () ->new ResourceNotFoundException("Transaction not found with id: " + id));
        return transactionMapper.toResponse(transaction);
    }

    public TransactionResponse create(TransactionCreateRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        Customer customer = customerRepository.findById(request.getCustomerId()).orElse(null);
        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElse(null);
        Transaction transaction = transactionMapper
                .toEntity(request, category, customer, supplier);
        Transaction saved = transactionRepository.save(transaction);
        return transactionMapper.toResponse(saved);

    }
    public TransactionResponse update(TransactionUpdateRequest request, Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Transaction not found with id: " + id));
        EntityUtil.copyNoNullProperties(request,transaction);
        Transaction saved = transactionRepository.save(transaction);
        return transactionMapper.toResponse(saved);
    }
    public void delete(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Transaction not found with id: " + id));
        transactionRepository.delete(transaction);
    }
}
