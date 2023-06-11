package com.example.learn_with_us.data.repository;

import com.example.learn_with_us.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@code UserRepository} interface is a repository interface for managing user entities.
 * It extends the {@code JpaRepository} interface and provides CRUD operations for the {@code User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
