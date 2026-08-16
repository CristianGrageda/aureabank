package com.gr.aureabank.services;

import com.gr.aureabank.dtos.AccountDto;
import com.gr.aureabank.dtos.AccountMovementDto;
import com.gr.aureabank.dtos.AccountRequest;
import com.gr.aureabank.entities.Account;
import com.gr.aureabank.entities.User;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountRequest account, Long userId);
    List<AccountDto> findAllByUser(Long userId);
    AccountDto findByIdAndUser(Long id, Long userId);
    List<AccountMovementDto> findMovements(Long id, Long userId);
}
