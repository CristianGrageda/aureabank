package com.gr.aureabank.dtos;

import com.gr.aureabank.enums.AccountStatusEnum;
import com.gr.aureabank.enums.CurrencyEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDto {
    private BigDecimal balance;
    private CurrencyEnum currency;
    private AccountStatusEnum status;
}
