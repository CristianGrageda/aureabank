package com.gr.aureabank.controllers;

import com.gr.aureabank.dtos.AccountDto;
import com.gr.aureabank.dtos.AccountMovementDto;
import com.gr.aureabank.dtos.AccountRequest;
import com.gr.aureabank.entities.Account;
import com.gr.aureabank.security.UserMain;
import com.gr.aureabank.services.AccountService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public AccountDto createAccount(@RequestBody AccountRequest account, @AuthenticationPrincipal UserMain userMain) {
        return service.createAccount(account, userMain.getUser().getId());
    }

    @GetMapping
    public List<AccountDto> findAllAccounts(@AuthenticationPrincipal UserMain userMain){
        return service.findAllByUser(userMain.getUser().getId());
    }

    @GetMapping("/{accountId}")
    public AccountDto findAccount(@PathVariable Long accountId, @AuthenticationPrincipal UserMain userMain){
        return service.findByIdAndUser(accountId, userMain.getUser().getId());
    }

    @GetMapping("/{accountId}/movements")
    public List<AccountMovementDto> findMovements(@PathVariable Long accountId, @AuthenticationPrincipal UserMain userMain){
        return service.findMovements(accountId, userMain.getUser().getId());
    }
}
