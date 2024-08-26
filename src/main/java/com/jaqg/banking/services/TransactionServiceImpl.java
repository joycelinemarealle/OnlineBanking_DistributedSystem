package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountRepository accountRepository;

    public TransactionResponse transfer(int fromAccount, int fromAccountSortCode, int toAccount, int toAccountSortCode, double amount) {
         Account from = accountRepository.findAccountByNumber(fromAccount);
         Account to = accountRepository.findAccountByNumber(toAccount);
//         if(from.getBalance() >= amount) {
//             from.withdraw(amount);
//              to.deposit(amount);
//              transactionRepo.save(from);
//              transactionRepo.save(to);
//         }
         //return new TransactionResponse(..........);
        return null;
    }

    @Override
    public TransactionResponse withdraw(TransactionRequest request) {
        return  null;
    }

    public TransactionResponse executeTransfer(TransactionRequest request) {
        request.getAmount();
        return null;
    }

    public TransactionResponse deposit(TransactionRequest request) {
        return  null;
    }


}
