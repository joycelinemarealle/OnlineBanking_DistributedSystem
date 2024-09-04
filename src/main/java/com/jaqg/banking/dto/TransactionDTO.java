package com.jaqg.banking.dto;

import com.jaqg.banking.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        LocalDateTime time,
        OperationType type,
        Long fromAccount,
        Integer fromAccountSortCode,
        Long toAccount,
        Integer toAccountSortCode,
        BigDecimal amount) {
}