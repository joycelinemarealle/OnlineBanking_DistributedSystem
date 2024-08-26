package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public TransactionResponse transfer(int fromAccount, int fromAccountSortCode, int toAccount, int toAccountSortCode, double amount) {
         Account from = transactionRepo.getAccount(fromAccount, fromAccountSortCode);
         Account to = transactionRepo.getAccount(toAccount, toAccountSortCode);
         if(from.getBalance() >= amount) {
             from.withdraw(amount);
              to.deposit(amount);
              transactionRepo.save(from);
              transactionRepo.save(to);
         }
         //return new TransactionResponse(..........);
    }

    public TransactionResponse withdraw(TransactionRequest request) {

    }

    public TransactionResponse executeTransfer(TransactionRequest request) {
        request.getAmount();
        return null;
    }

    public TransactionResponse deposit(TransactionRequest request) {

    }


}
