package com.jaqg.banking.dto;

import java.math.BigDecimal;
import java.util.List;

public record AccountResponseDTO(
        Long number,
        Integer sortCode,
        String name,
        BigDecimal openingBalance,
        List<TransactionResponse> transactions,
        BigDecimal balance,
        Long customerId) {
}
