package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
//    public TransactionResponse transfer(Long fromAccount, Long fromAccountSortCode, Long toAccount, Long toAccountSortCode, BigDecimal amount);
    public TransactionResponse withdraw(TransactionRequest request);
    public TransactionResponse executeTransfer(TransactionRequest request);
    public TransactionResponse deposit(TransactionRequest request);
    public List<TransactionResponse> getAllTransactions();
}
