package com.gr.aureabank.services;

import com.gr.aureabank.dtos.AccountDto;
import com.gr.aureabank.dtos.AccountMovementDto;
import com.gr.aureabank.entities.Account;
import com.gr.aureabank.entities.AccountMovement;
import com.gr.aureabank.repositories.AccountMovementRepository;
import com.gr.aureabank.repositories.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final AccountMovementRepository accountMovementRepository;

    public AccountServiceImpl(AccountRepository repository, AccountMovementRepository accountMovementRepository) {
        this.repository = repository;
        this.accountMovementRepository = accountMovementRepository;
    }

    @Override
    public List<AccountDto> findAllByUser(Long id) {
        return repository.findAllByUserId(id)
                .stream().map(this::toAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findByIdAndUser(Long id, Long userId) {
        Account account = repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada"));
        return toAccountDto(account);
    }

    @Override
    public List<AccountMovementDto> findMovements(Long id, Long userId) {
        return accountMovementRepository.findAllByAccountIdAndAccountUserId(id, userId)
                .stream().map(this::toAccountMovementDto)
                .collect(Collectors.toList());
    }

    private AccountDto toAccountDto(Account account) {
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setBalance(account.getBalance());
        dto.setCurrency(account.getCurrency());
        dto.setStatus(account.getStatus());
        return dto;
    }

    private AccountMovementDto toAccountMovementDto(AccountMovement account) {
        AccountMovementDto dto = new AccountMovementDto();
        dto.setAmount(account.getAmount());
        dto.setCreatedAt(account.getCreatedAt());
        dto.setId(account.getId());
        dto.setType(account.getType());
        return dto;
    }
}
