package com.pgh.huutinhdoor.controller.admin;

import com.pgh.huutinhdoor.dto.request.TransactionCreateRequest;
import com.pgh.huutinhdoor.dto.request.TransactionUpdateRequest;
import com.pgh.huutinhdoor.dto.response.admin.TransactionResponse;
import com.pgh.huutinhdoor.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/transactions")
@RequiredArgsConstructor
public class AdminTransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAll(){
        return  ResponseEntity.ok(transactionService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getById(@PathVariable Long id){
        TransactionResponse result = transactionService.findByIdOrThrow(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@RequestBody TransactionCreateRequest request) {
        TransactionResponse response = transactionService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/admin/transactions/" + response.getId()))
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> update(
            @RequestBody TransactionUpdateRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(transactionService.update(request,id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        transactionService.delete(id);
        ResponseEntity.noContent().build();
    }


}
