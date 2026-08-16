package com.gr.aureabank.services;

import com.gr.aureabank.dtos.UserDto;
import com.gr.aureabank.dtos.UserRequest;
import com.gr.aureabank.entities.User;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto createUser(UserRequest user);
}
