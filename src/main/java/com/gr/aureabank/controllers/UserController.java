package com.gr.aureabank.controllers;

import com.gr.aureabank.dtos.UserDto;
import com.gr.aureabank.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDto> findAll() {
        return service.findAll();
    }

}
