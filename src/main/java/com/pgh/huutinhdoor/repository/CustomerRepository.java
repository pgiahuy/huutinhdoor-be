package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @EntityGraph(attributePaths = {"user"})
    List<Customer> findAll();
}
