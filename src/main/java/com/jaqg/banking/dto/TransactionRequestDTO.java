package com.jaqg.banking.dto;

import com.jaqg.banking.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record TransactionRequestDTO(
        TransactionType type,
        Long fromAccount,
        Integer fromAccountSortCode,
        Long toAccount,
        Integer toAccountSortCode,
        @NotNull
        @PositiveOrZero
        BigDecimal amount) {
}