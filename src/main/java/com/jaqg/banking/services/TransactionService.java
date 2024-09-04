package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionRequestDTO;
import com.jaqg.banking.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    //    public TransactionResponse transfer(Long fromAccount, Long fromAccountSortCode, Long toAccount, Long toAccountSortCode, BigDecimal amount);
    TransactionDTO withdraw(TransactionRequestDTO request);

    TransactionDTO executeTransfer(TransactionRequestDTO request);

    TransactionDTO deposit(TransactionRequestDTO request);

    List<TransactionDTO> getAllTransactions();
}
