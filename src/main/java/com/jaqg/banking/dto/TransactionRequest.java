package com.jaqg.banking.dto;

import com.jaqg.banking.entities.OperationType;

import java.math.BigDecimal;

public record TransactionRequest (
        OperationType type,
        Long fromAcount,
        Long fromAcountSortCode,
        Long toAcount,
        Long toAcountSortCode,
        BigDecimal amount){}
