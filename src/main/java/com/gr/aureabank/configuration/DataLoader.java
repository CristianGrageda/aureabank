package com.gr.aureabank.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gr.aureabank.repository.AccountRepository;
import com.gr.aureabank.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner testRepository(UserRepository userRepository, AccountRepository accountRepository) {
        return args -> {
            System.out.println("=== DATA ===");
            userRepository.findAll().forEach(System.out::println);
            accountRepository.findAll().forEach(System.out::println);
        };
    }
}