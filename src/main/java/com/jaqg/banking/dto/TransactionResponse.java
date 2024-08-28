package com.jaqg.banking.dto;

import com.jaqg.banking.entities.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        LocalDateTime time,
        OperationType type,
        Long fromAccount,
        Integer fromAccountSortCode,
        Long toAccount,
        Integer toAccountSortCode,
        BigDecimal amount) {}