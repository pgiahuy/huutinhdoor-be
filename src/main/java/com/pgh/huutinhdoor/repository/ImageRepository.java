package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.Image;
import com.pgh.huutinhdoor.enums.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByTargetIdAndTargetTypeAndIsPrimaryTrue(Long id, TargetType targetType);

    List<Image> findByTargetTypeAndTargetIdInAndIsPrimaryTrue(
            TargetType targetType,
            List<Long> targetIds
    );

    List<Image> findAllByTargetIdAndTargetType(Long targetId, TargetType targetType);
}
