package com.jaqg.banking.dto;

import com.jaqg.banking.entities.OperationType;
import org.springframework.expression.Operation;
import org.springframework.expression.spel.ast.OpAnd;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransactionResponse(
        LocalDateTime time,
        OperationType type,
        Long fromAccount,
        Integer fromAccountSortCode,
        Long toAccount,
        Integer toAccountSortCode,
        BigDecimal amount) {}