package com.gr.aureabank.dtos;

import com.gr.aureabank.enums.CurrencyEnum;
import com.gr.aureabank.enums.TransactionStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDto {
    private Long id;
    private BigDecimal amount;
    private CurrencyEnum currency;
    private String description;
    private TransactionStatusEnum status;
    private LocalDateTime createdAt;
}
