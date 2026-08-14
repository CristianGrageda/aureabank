package com.gr.aureabank.configuration;

import com.gr.aureabank.entities.User;
import com.gr.aureabank.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner testRepository(SecurityBeansConfig config, UserRepository userRepository) {
        return args -> {
            /*
            User user1 = userRepository.findById(1L).orElse(new User());
            User user2 = userRepository.findById(2L).orElse(new User());
            user1.setPasswordHash(config.passwordEncoder().encode("1234"));
            user2.setPasswordHash(config.passwordEncoder().encode("4321"));
            userRepository.save(user1);
            userRepository.save(user2);
             */
        };
    }
}