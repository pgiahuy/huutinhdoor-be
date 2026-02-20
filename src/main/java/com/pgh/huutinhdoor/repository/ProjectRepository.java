package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("""
    SELECT DISTINCT p FROM Project p
    LEFT JOIN FETCH p.ticket
    LEFT JOIN FETCH p.categories
    """)
    List<Project> findAllWithRelations();
}
