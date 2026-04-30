package com.gr.aureabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
