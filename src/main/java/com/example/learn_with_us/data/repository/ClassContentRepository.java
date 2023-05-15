package com.example.learn_with_us.data.repository;

import com.example.learn_with_us.data.entity.BaseContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassContentRepository extends JpaRepository<BaseContent, Long> {
}