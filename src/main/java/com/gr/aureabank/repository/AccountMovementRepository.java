package com.gr.aureabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entity.AccountMovement;

public interface AccountMovementRepository extends JpaRepository<AccountMovement, Long> {

}
