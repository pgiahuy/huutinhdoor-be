package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.Project;
import com.pgh.huutinhdoor.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.referenceId = :refId AND t.referenceType = :refType")
    Double sumAmountByReferenceIdAndType(Long refId, String refType);

    @EntityGraph(attributePaths = {"category", "customer", "supplier"})
    List<Transaction> findAll();
}