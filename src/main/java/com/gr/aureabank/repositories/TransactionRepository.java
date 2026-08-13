package com.gr.aureabank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
