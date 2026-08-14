package com.gr.aureabank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
