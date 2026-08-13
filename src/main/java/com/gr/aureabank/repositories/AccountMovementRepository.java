package com.gr.aureabank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entities.AccountMovement;

public interface AccountMovementRepository extends JpaRepository<AccountMovement, Long> {

}
