package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @EntityGraph(attributePaths = {"user"})
    List<Customer> findAll();

    Optional<Customer> findByPhone(String phone);

    Boolean existsByPhone(String phone);
}
