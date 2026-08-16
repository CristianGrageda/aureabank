package com.gr.aureabank.services;

import com.gr.aureabank.dtos.TransactionDto;
import com.gr.aureabank.dtos.TransferRequest;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> findAllByUser(Long userId);
    TransactionDto findByIdAndUser(Long id, Long userId);
    TransactionDto transfer(TransferRequest request, Long requestingUserId);
}
