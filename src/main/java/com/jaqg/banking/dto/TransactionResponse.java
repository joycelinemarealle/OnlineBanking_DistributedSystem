package com.jaqg.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransactionResponse(
        LocalDateTime time,
        String type,
        Long fromAccount,
        Integer fromAccountSortCode,
        Long toAccount,
        Integer toAccountSortCode,
        BigDecimal amount) {}