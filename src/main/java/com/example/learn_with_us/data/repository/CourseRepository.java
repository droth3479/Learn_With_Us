package com.example.learn_with_us.data.repository;

import com.example.learn_with_us.data.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The {@code CourseRepository} interface is a repository interface for managing class content entities.
 * It extends the {@code JpaRepository} interface and provides CRUD operations for the {@code Course} entity.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findCourseByName(String name);
}
