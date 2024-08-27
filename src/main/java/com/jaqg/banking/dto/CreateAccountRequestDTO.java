package com.jaqg.banking.dto;

public record CreateAccountRequestDTO(
      Long customerId,
      String accountName,
      Double openingBalance
) {
}
