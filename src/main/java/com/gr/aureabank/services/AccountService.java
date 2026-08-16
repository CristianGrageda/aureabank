package com.gr.aureabank.services;

import com.gr.aureabank.dtos.AccountDto;
import com.gr.aureabank.dtos.AccountMovementDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> findAllByUser(Long userId);
    AccountDto findByIdAndUser(Long id, Long userId);
    List<AccountMovementDto> findMovements(Long id, Long userId);
}
