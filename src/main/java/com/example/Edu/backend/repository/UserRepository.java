package com.example.Edu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Edu.backend.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    
} 
