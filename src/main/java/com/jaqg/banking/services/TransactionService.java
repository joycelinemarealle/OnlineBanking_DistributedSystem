package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;

import java.util.List;

public interface TransactionService {
    //    public TransactionResponse transfer(Long fromAccount, Long fromAccountSortCode, Long toAccount, Long toAccountSortCode, BigDecimal amount);
    TransactionResponse withdraw(TransactionRequest request);

    TransactionResponse executeTransfer(TransactionRequest request);

    TransactionResponse deposit(TransactionRequest request);

    List<TransactionResponse> getAllTransactions();
}
