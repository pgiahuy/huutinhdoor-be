package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
