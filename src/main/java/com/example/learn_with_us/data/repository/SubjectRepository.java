package com.example.learn_with_us.data.repository;

import com.example.learn_with_us.data.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The {@code SubjectRepository} interface is a repository interface for managing subject entities.
 * It extends the {@code JpaRepository} interface and provides CRUD operations for the {@code Subject} entity.
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findSubjectById(Long id);
}
