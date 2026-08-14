package com.gr.aureabank.controllers;

import com.gr.aureabank.dtos.AccountDto;
import com.gr.aureabank.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public List<AccountDto>  findAllByUserId(@PathVariable Long userId){
        return service.findAllByUser(userId);
    }
}
