package com.gr.aureabank.services;

import com.gr.aureabank.configuration.SecurityBeansConfig;
import com.gr.aureabank.dtos.UserDto;
import com.gr.aureabank.dtos.UserRequest;
import com.gr.aureabank.entities.User;
import com.gr.aureabank.enums.UserStatusEnum;
import com.gr.aureabank.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final SecurityBeansConfig config;

    public UserServiceImpl(UserRepository repository, SecurityBeansConfig config) {
        this.repository = repository;
        this.config = config;
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserRequest user) {
        User newUser = new User();
        newUser.setDni(user.getDni());
        newUser.setEmail(user.getEmail());
        newUser.setPasswordHash(config.passwordEncoder().encode(user.getPassword()));
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setStatus(UserStatusEnum.ACTIVE);
        return toDto(repository.save(newUser));
    }

    private UserDto toDto(User user){
        UserDto dto = new UserDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }
}
