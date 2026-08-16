package com.gr.aureabank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserId(Long userId);
    Optional<Account> findByIdAndUserId(Long id, Long userId);
    Optional<Account> findAccountByCbu(String cbu);
}
