package com.pgh.huutinhdoor.entity;

import com.pgh.huutinhdoor.enums.TargetType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String publicId;

    private Boolean isPrimary = false;

    private Long targetId;
    @Enumerated(EnumType.STRING)
    private TargetType targetType;
}