package com.pgh.huutinhdoor.service;


import com.pgh.huutinhdoor.dto.request.TransactionCreateRequest;
import com.pgh.huutinhdoor.dto.request.TransactionUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.TransactionResponse;
import com.pgh.huutinhdoor.entity.Product;
import com.pgh.huutinhdoor.entity.Transaction;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.mapper.TransactionMapper;
import com.pgh.huutinhdoor.repository.ProductRepository;
import com.pgh.huutinhdoor.repository.TransactionRepository;
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
        return null;
    }
    public TransactionResponse update(TransactionUpdateRequest request) {
        return null;
    }
    public void delete(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Transaction not found with id: " + id));
        transactionRepository.delete(transaction);
    }
}
