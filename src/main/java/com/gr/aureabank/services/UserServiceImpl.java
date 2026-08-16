package com.gr.aureabank.services;

import com.gr.aureabank.configuration.SecurityBeansConfig;
import com.gr.aureabank.dtos.UserDto;
import com.gr.aureabank.dtos.UserRequest;
import com.gr.aureabank.entities.User;
import com.gr.aureabank.enums.UserStatusEnum;
import com.gr.aureabank.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserRequest userRequest) {
        if (repository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        User user = new User();
        user.setDni(userRequest.getDni());
        user.setEmail(userRequest.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setStatus(UserStatusEnum.ACTIVE);
        return toDto(repository.save(user));
    }

    private UserDto toDto(User user){
        UserDto dto = new UserDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }
}
