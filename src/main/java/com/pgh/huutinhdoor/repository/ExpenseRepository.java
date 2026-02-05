package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double getTotalExpense();
}
