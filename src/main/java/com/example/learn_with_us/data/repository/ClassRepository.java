package com.example.learn_with_us.data.repository;

import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The {@code ClassRepository} interface is a repository interface for managing class content entities.
 * It extends the {@code JpaRepository} interface and provides CRUD operations for the {@code Class} entity.
 */
@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> findByNameAndCourse(String className, Course course);
    List<Class> findByCourse(Course course);
}