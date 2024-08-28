package com.jaqg.banking.dto;

import com.jaqg.banking.entities.OperationType;

import java.math.BigDecimal;

public record TransactionRequest (
        OperationType type,
        Long fromAccount,
        Long fromAccountSortCode,
        Long toAccount,
        Long toAccountSortCode,
        BigDecimal amount){}
