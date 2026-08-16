package com.gr.aureabank.services;

import com.gr.aureabank.dtos.TransactionDto;
import com.gr.aureabank.dtos.TransferRequest;
import com.gr.aureabank.entities.Account;
import com.gr.aureabank.entities.AccountMovement;
import com.gr.aureabank.entities.Transaction;
import com.gr.aureabank.enums.MovementTypeEnum;
import com.gr.aureabank.enums.TransactionStatusEnum;
import com.gr.aureabank.repositories.AccountMovementRepository;
import com.gr.aureabank.repositories.AccountRepository;
import com.gr.aureabank.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final AccountMovementRepository movementRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountRepository accountRepository,
                                  AccountMovementRepository movementRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }


    @Override
    @Transactional
    public TransactionDto transfer(TransferRequest request, Long requestingUserId) {
        Account source = accountRepository.findByIdAndUserId(request.getSourceAccountId(), requestingUserId)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta origen no encontrada"));

        Account destination = accountRepository.findById(request.getDestinationAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta destino no encontrada"));

        if (source.getId().equals(destination.getId())) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }

        if (source.getCurrency() != destination.getCurrency()) {
            throw new IllegalArgumentException("Las cuentas deben tener la misma moneda");
        }

        if (source.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        LocalDateTime dateExecute = LocalDateTime.now();

        // Guarda la Transaccion realizada (una transferencia)
        Transaction transaction = new Transaction();
        transaction.setSourceAccount(source);
        transaction.setDestinationAccount(destination);
        transaction.setAmount(request.getAmount());
        transaction.setCurrency(source.getCurrency());
        transaction.setDescription(request.getDescription());
        transaction.setStatus(TransactionStatusEnum.COMPLETED);
        transaction.setCreatedAt(dateExecute);
        transaction = transactionRepository.save(transaction);

        // Guarda la Cuenta origen con el nuevo monto (se resta monto transferido)
        BigDecimal newSourceBalance = source.getBalance().subtract(request.getAmount());
        source.setBalance(newSourceBalance);
        accountRepository.save(source);

        // Guarda el Movimiento realizado para la cuenta origen (envio de dinero)
        AccountMovement debit = new AccountMovement();
        debit.setAccount(source);
        debit.setTransaction(transaction);
        debit.setType(MovementTypeEnum.DEBIT);
        debit.setAmount(request.getAmount());
        debit.setBalanceAfter(newSourceBalance);
        debit.setCreatedAt(dateExecute);
        movementRepository.save(debit);

        // Gurada la Cuenta destino con el nuevo monto (se suma monsto transferido)
        BigDecimal newDestinationBalance = destination.getBalance().add(request.getAmount());
        destination.setBalance(newDestinationBalance);
        accountRepository.save(destination);

        // Guarda el Movimiento realizado para la cuenta destino (recibo de dinero)
        AccountMovement credit = new AccountMovement();
        credit.setAccount(destination);
        credit.setTransaction(transaction);
        credit.setType(MovementTypeEnum.CREDIT);
        credit.setAmount(request.getAmount());
        credit.setBalanceAfter(newDestinationBalance);
        credit.setCreatedAt(dateExecute);
        movementRepository.save(credit);

        return toDto(transaction);
    }

    private TransactionDto toDto(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setAmount(transaction.getAmount());
        dto.setCreatedAt(transaction.getCreatedAt());
        dto.setCurrency(transaction.getCurrency());
        dto.setDescription(transaction.getDescription());
        dto.setStatus(transaction.getStatus());
        return dto;
    }
}
