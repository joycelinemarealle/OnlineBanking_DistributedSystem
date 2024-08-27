package com.jaqg.banking.DTO;

import com.jaqg.banking.entities.Transaction;

import java.math.BigDecimal;
import java.util.List;

public record GetAccountResponseDTO(
        Long number,
        Integer sortCode,
        String name,
        BigDecimal openingBalance,
        List<com.jaqg.banking.dto.TransactionResponse> transactions,
        BigDecimal balance,
        Long customerId) {
}
