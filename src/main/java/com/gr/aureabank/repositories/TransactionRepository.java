package com.gr.aureabank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entities.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.sourceAccount.user.id = :userId OR t.destinationAccount.user.id = :userId ORDER BY t.createdAt DESC")
    List<Transaction> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT t FROM Transaction t WHERE t.id = :id AND (t.sourceAccount.user.id = :userId OR t.destinationAccount.user.id = :userId)")
    Optional<Transaction> findByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);
}
