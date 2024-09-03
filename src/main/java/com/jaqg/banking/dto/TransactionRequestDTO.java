package com.jaqg.banking.dto;

import com.jaqg.banking.enums.OperationType;

import java.math.BigDecimal;

public record TransactionRequestDTO(
        OperationType type,
        Long fromAccount,
        Integer fromAccountSortCode,
        Long toAccount,
        Integer toAccountSortCode,
        BigDecimal amount) {
}