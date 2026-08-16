package com.gr.aureabank.services;

import com.gr.aureabank.dtos.AccountDto;
import com.gr.aureabank.entities.Account;
import com.gr.aureabank.repositories.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public AccountDto findByIdAndUser(Long id, Long userId) {
        Account account = repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada"));
        return toAccountDto(account);
    }

    private AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setBalance(account.getBalance());
        accountDto.setCurrency(account.getCurrency());
        accountDto.setStatus(account.getStatus());
        return accountDto;
    }
}
