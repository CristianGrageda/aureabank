package com.gr.aureabank.controllers;

import com.gr.aureabank.dtos.LoginRequest;
import com.gr.aureabank.dtos.LoginResponse;
import com.gr.aureabank.dtos.UserDto;
import com.gr.aureabank.dtos.UserRequest;
import com.gr.aureabank.security.UserMain;
import com.gr.aureabank.services.JwtService;
import com.gr.aureabank.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserService service;

    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JwtService jwtService,
                          UserService service) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.service = service;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken((UserMain) userDetails);

        return new LoginResponse(token);
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody UserRequest userRequest) {
        return service.createUser(userRequest);
    }
}
