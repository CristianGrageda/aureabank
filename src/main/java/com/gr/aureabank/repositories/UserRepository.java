package com.gr.aureabank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {}
