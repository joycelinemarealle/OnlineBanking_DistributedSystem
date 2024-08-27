package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;

import java.util.List;

public interface TransactionService {
    public TransactionResponse transfer(int fromAccount, int fromAccountSortCode, int toAccount, int toAccountSortCode, double amount);
    public TransactionResponse withdraw(TransactionRequest request);
    public TransactionResponse executeTransfer(TransactionRequest request);
    public TransactionResponse deposit(TransactionRequest request);
    public List<TransactionResponse> getAllTransactions();
}
