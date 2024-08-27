package com.jaqg.banking.DTO;

public record CreateAccountRequestDTO(
      Long customerId,
      String accountName,
      Double openingBalance
) {
}
