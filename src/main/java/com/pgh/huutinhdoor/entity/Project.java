package com.pgh.huutinhdoor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "projects")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true)
    private String slug;

    @Column
    private List<String> tags;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean isPublished = false;

    private LocalDateTime publishedAt;

    private Long sourceTicketId;

    private String location;

    private String customerName;

    private Integer viewCount = 0;

    private LocalDateTime completedAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.publishedAt = LocalDateTime.now();
        this.slug = generateSlug(this.title);
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.slug = generateSlug(this.title);
    }

    private String generateSlug(String title) {
        if (title == null) return null;
        String normalized = Normalizer.normalize(title, Normalizer.Form.NFD);
        return normalized.toLowerCase()
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("^-|-$", "");
    }
}