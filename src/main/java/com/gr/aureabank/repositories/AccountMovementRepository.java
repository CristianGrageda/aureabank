package com.gr.aureabank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entities.AccountMovement;

import java.util.List;

public interface AccountMovementRepository extends JpaRepository<AccountMovement, Long> {
    List<AccountMovement> findAllByAccountIdAndAccountUserId(Long accountId, Long accountUserId);
}
