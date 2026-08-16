package com.gr.aureabank.dtos;

import com.gr.aureabank.enums.CurrencyEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {
    private CurrencyEnum currency;
}
