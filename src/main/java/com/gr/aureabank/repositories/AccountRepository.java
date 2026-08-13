package com.gr.aureabank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.aureabank.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
