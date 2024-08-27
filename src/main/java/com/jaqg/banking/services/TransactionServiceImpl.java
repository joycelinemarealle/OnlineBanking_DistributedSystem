package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.OperationType;
import com.jaqg.banking.entities.Transaction;
import com.jaqg.banking.mapper.TransactionMapper;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public TransactionResponse transfer(int fromAccount, int fromAccountSortCode, int toAccount, int toAccountSortCode, double amount) {
        return null;
    }

    @Override
    public TransactionResponse withdraw(TransactionRequest request) {
        Account account = accountRepository.findAccountByNumber(request.toAcount());
        Transaction transaction = new Transaction(LocalDateTime.now(), request.amount(), OperationType.valueOf(request.type()), account);
        account.getTransactions().add(new Transaction(LocalDateTime.now(), request.amount(), OperationType.valueOf(request.type()), account));
        return new TransactionResponse(
                transaction.getDateTime(),
                OperationType.WITHDRAWAL.name(),
                (long)request.fromAcount(),
                (long)request.fromAccountSortCode(),
                account.getNumber(),
                account.getSortCode(),
                transaction.getTransVal());
    }

    public TransactionResponse executeTransfer(TransactionRequest request) {
        Account account = accountRepository.findAccountByNumber(request.toAcount());
        Transaction transaction = new Transaction(LocalDateTime.now(), request.amount(), OperationType.valueOf(request.type()), account);
        account.getTransactions().add(new Transaction(LocalDateTime.now(), request.amount(), OperationType.valueOf(request.type()), account));
        accountRepository.saveAccount(account);
        return transactionToTransactionResponse(transaction);
//        return new TransactionResponse(
//                transaction.getDateTime(),
//                OperationType.TRANSFER.name(),
//                (long)request.fromAcount(),
//                request.fromAcountSortCode(),
//                account.getNumber(),
//                account.getSortCode(),
//                transaction.getTransVal()
//        );
    }

    public TransactionResponse deposit(TransactionRequest request) {
        Account account = accountRepository.findAccountByNumber(request.toAcount());
        Transaction transaction = new Transaction(LocalDateTime.now(), request.amount(), OperationType.valueOf(request.type()), account);
        account.getTransactions().add(new Transaction(LocalDateTime.now(), request.amount(), OperationType.valueOf(request.type()), account));
        accountRepository.saveAccount(account);
        return  new TransactionResponse(transaction.getDateTime(),OperationType.DEPOSIT.name(),null, null, account.getNumber(),account.getSortCode(),transaction.getTransVal());
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        List<Transaction> transactions = transactionRepo.findAll();
        return transactions.stream().map(this::transactionToTransactionResponse).toList();

    }


    //this will go on the mapper for reusability and it will be public
    private TransactionResponse transactionToTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getDateTime(),
                OperationType.TRANSFER.name(),
                transaction.getRecipient().getNumber(),
                transaction.getRecipient().getSortCode(),
                transaction.getRecipient().getNumber(),//sender
                transaction.getRecipient().getSortCode(),//sender
                transaction.getTransVal());

    }


}
