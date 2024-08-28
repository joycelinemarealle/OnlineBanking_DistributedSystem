package com.jaqg.banking.dto;

import java.math.BigDecimal;

public record CreateAccountRequestDTO(
      Long customerId,
      String accountName,
      BigDecimal openingBalance
) {
}
