package com.example.ebookshop.repository;

import com.example.ebookshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    // Spring will automatically implement this based on the method name!
    Optional<User> findById(int id);

    boolean existsByUsername(String username);
    
    Optional<User> findByUsername(String username);
}