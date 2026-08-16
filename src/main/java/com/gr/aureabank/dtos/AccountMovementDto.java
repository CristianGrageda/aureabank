package com.gr.aureabank.dtos;

import com.gr.aureabank.enums.MovementTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountMovementDto {
    private Long id;
    private MovementTypeEnum type;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
