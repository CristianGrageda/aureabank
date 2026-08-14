package com.gr.aureabank.services;

import com.gr.aureabank.dtos.AccountDto;
import com.gr.aureabank.entities.Account;
import com.gr.aureabank.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AccountDto> findAllByUser(Long id) {
        return repository.findAllByUserId(id).stream().map(this::toAccountDto).collect(Collectors.toList());
    }

    private AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setBalance(account.getBalance());
        accountDto.setCurrency(account.getCurrency());
        accountDto.setStatus(account.getStatus());
        return accountDto;
    }
}
