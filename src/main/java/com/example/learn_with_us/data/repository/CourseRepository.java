package com.example.learn_with_us.data.repository;

import com.example.learn_with_us.data.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select c from Course c " +
            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.founder) like lower(concat('%', :searchTerm, '%'))")
    List<Course> search(@Param("searchTerm") String searchTerm);
}
