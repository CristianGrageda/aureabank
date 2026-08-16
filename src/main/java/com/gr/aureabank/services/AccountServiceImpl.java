package com.gr.aureabank.services;

import com.gr.aureabank.dtos.AccountDto;
import com.gr.aureabank.dtos.AccountMovementDto;
import com.gr.aureabank.dtos.AccountRequest;
import com.gr.aureabank.entities.Account;
import com.gr.aureabank.entities.AccountMovement;
import com.gr.aureabank.entities.User;
import com.gr.aureabank.enums.AccountStatusEnum;
import com.gr.aureabank.repositories.AccountMovementRepository;
import com.gr.aureabank.repositories.AccountRepository;
import com.gr.aureabank.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private static final List<String> WORDS = List.of(
            "sol", "luna", "rio", "monte", "cielo", "piedra", "verde", "azul",
            "norte", "sur", "lago", "campo", "nube", "fuego", "arena"
    );

    private final AccountRepository repository;
    private final AccountMovementRepository accountMovementRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository repository,
                              AccountMovementRepository accountMovementRepository,
                              UserRepository userRepository) {
        this.repository = repository;
        this.accountMovementRepository = accountMovementRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AccountDto createAccount(AccountRequest accountRequest, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Account account = new Account();
        account.setUser(user);
        account.setAlias(generateAlias());
        account.setCbu(generateUniqueCbu());
        account.setBalance(BigDecimal.ZERO);
        account.setCurrency(accountRequest.getCurrency());
        account.setStatus(AccountStatusEnum.ACTIVE);
        account.setCreatedAt(LocalDateTime.now());
        return toAccountDto(repository.save(account));
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
        dto.setAlias(account.getAlias());
        dto.setCbu(account.getCbu());
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

    private String generateCbu() {
        Random random = new Random();
        StringBuilder cbu = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            cbu.append(random.nextInt(10));
        }
        return cbu.toString();
    }

    private String generateUniqueCbu() {
        String cbu;
        do {
            cbu = generateCbu();
        } while (repository.findAccountByCbu(cbu).isPresent());
        return cbu;
    }

    private String generateAlias() {
        Random random = new Random();
        return WORDS.get(random.nextInt(WORDS.size())) + "." +
                WORDS.get(random.nextInt(WORDS.size())) + "." +
                WORDS.get(random.nextInt(WORDS.size()));
    }
}
