package com.gr.aureabank.services;

import com.gr.aureabank.dtos.TransactionDto;
import com.gr.aureabank.dtos.TransferRequest;

public interface TransactionService {
    TransactionDto transfer(TransferRequest request, Long requestingUserId);
}
