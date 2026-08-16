package com.gr.aureabank.controllers;

import com.gr.aureabank.dtos.AccountDto;
import com.gr.aureabank.security.UserMain;
import com.gr.aureabank.services.AccountService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping
    public List<AccountDto> findAllByUser(@AuthenticationPrincipal UserMain userMain){
        return service.findAllByUser(userMain.getUser().getId());
    }

    @GetMapping("/{accountId}")
    public AccountDto findByIdAndUser(@PathVariable Long accountId, @AuthenticationPrincipal UserMain userMain){
        return service.findByIdAndUser(accountId, userMain.getUser().getId());
    }
}
