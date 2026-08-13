package com.gr.aureabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
