package com.gr.aureabank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entities.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserId(Long userId);
}
