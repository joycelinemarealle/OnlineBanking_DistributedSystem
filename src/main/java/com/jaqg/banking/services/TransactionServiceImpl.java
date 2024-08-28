package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.OperationType;
import com.jaqg.banking.entities.Transaction;
import com.jaqg.banking.exceptions.TransactionNotFoundException;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jaqg.banking.mapper.TransactionMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public TransactionResponse transfer(int fromAccount, int fromAccountSortCode, int toAccount, int toAccountSortCode, double amount) {
        return null;
    }

    @Override
    public TransactionResponse withdraw(TransactionRequest request) {
        Optional<Account> optionalAccount = accountRepository.findById(request.toAccount());
        if (optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            Transaction transaction = new Transaction(LocalDateTime.now(), request.amount(), request.type(), account);
            account.addTransaction(transaction);
            accountRepository.save(account);
            return transactionMapper.transactionToTransactionResponse(transaction);
        } else {
            throw new TransactionNotFoundException("Transaction could not be found");
        }
    }

    public TransactionResponse executeTransfer(TransactionRequest request) {
        Optional<Account> optionalAccount = accountRepository.findById(request.toAccount());
        if (optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            Transaction transaction = new Transaction(LocalDateTime.now(), request.amount(), request.type(), account);
            account.addTransaction(transaction);
            accountRepository.save(account);
            return transactionMapper.transactionToTransactionResponse(transaction);
        } else {
            throw new TransactionNotFoundException("Transaction could not be found");
        }
    }

    public TransactionResponse deposit(TransactionRequest request) {
        Optional<Account> optionalAccount = accountRepository.findById(request.toAccount());
        if (optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            Transaction transaction = new Transaction(LocalDateTime.now(), request.amount(), request.type(), account);
            account.addTransaction(transaction);
            accountRepository.save(account);
            return transactionMapper.transactionToTransactionResponse(transaction);
        } else {
            throw new TransactionNotFoundException("Transaction could not be found");
        }
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        List<Transaction> transactions = transactionRepo.findAll();
        return transactions.stream().map(e -> transactionMapper.transactionToTransactionResponse(e)).toList();
    }


}
