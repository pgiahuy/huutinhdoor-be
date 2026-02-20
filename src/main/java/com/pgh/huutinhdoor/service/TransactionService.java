package com.pgh.huutinhdoor.service;


import com.pgh.huutinhdoor.dto.request.TransactionCreateRequest;
import com.pgh.huutinhdoor.dto.request.TransactionUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.TransactionResponse;
import com.pgh.huutinhdoor.entity.Transaction;
import com.pgh.huutinhdoor.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<TransactionResponse> getAll() {
        return null;
    }

    public TransactionResponse findByIdOrThrow(Long categoryId) {
        return null;
    }

    public TransactionResponse create(TransactionCreateRequest request) {
        return null;
    }
    public TransactionResponse update(TransactionUpdateRequest request) {
        return null;
    }
    public Boolean delete(Long id) {
        return null;
    }
}
