package com.example.learn_with_us.data.repository;

import com.example.learn_with_us.data.entity.BaseContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@code ClassContentRepository} interface is a repository interface for managing class content entities.
 * It extends the {@code JpaRepository} interface and provides CRUD operations for the {@code BaseContent} entity.
 */
@Repository
public interface ClassContentRepository extends JpaRepository<BaseContent, Long> {
    void deleteById(Long Id);
}