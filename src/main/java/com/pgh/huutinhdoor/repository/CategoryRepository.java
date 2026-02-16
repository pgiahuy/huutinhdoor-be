package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
