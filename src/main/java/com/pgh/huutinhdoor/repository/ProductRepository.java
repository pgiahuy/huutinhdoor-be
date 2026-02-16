package com.pgh.huutinhdoor.repository;


import com.pgh.huutinhdoor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category")
    List<Product> findAllWithCategory();

    List<Product> findAllByCategory_Id(Long categoryId);

}
