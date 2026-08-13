package com.gr.aureabank.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gr.aureabank.repositories.AccountMovementRepository;
import com.gr.aureabank.repositories.AccountRepository;
import com.gr.aureabank.repositories.TransactionRepository;
import com.gr.aureabank.repositories.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner testRepository(UserRepository userRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, AccountMovementRepository accountMovementRepository) {
        return args -> {
            System.out.println("=== DATA ===");
            userRepository.findAll().forEach(System.out::println);
            accountRepository.findAll().forEach(System.out::println);
            transactionRepository.findAll().forEach(System.out::println);
            accountMovementRepository.findAll().forEach(System.out::println);
        };
    }
}