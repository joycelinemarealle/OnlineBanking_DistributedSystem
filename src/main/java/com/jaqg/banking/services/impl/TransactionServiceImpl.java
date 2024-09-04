package com.jaqg.banking.services.impl;

import com.jaqg.banking.dto.TransactionDTO;
import com.jaqg.banking.dto.TransactionRequestDTO;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Transaction;
import com.jaqg.banking.exceptions.AccountNotFoundException;
import com.jaqg.banking.exceptions.NotEnoughFundsException;
import com.jaqg.banking.mapper.TransactionsMapper;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.TransactionRepository;
import com.jaqg.banking.services.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDTO withdraw(TransactionRequestDTO request) {
        final Account account = accountRepository.findByIdNumberAndIsClosedFalse(request.fromAccount())
                .orElseThrow(() -> new AccountNotFoundException(request.fromAccount()));

        BigDecimal newBalance = account.getBalance().subtract(request.amount());
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughFundsException(request.fromAccount());
        }

        Transaction transaction = new Transaction(request.amount(), request.type(), null, account);
        transactionRepository.save(transaction);

        account.setBalance(newBalance);
        accountRepository.save(account);

        return TransactionsMapper.mapToDTO(transaction);
    }

    public TransactionDTO executeTransfer(TransactionRequestDTO request) {
        final Account sender = accountRepository.findByIdNumberAndIsClosedFalse(request.fromAccount())
                .orElseThrow(() -> new AccountNotFoundException(request.fromAccount()));
        final Account recipient = accountRepository.findByIdNumberAndIsClosedFalse(request.toAccount())
                .orElseThrow(() -> new AccountNotFoundException(request.toAccount()));

        BigDecimal newBalance = sender.getBalance().subtract(request.amount());
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughFundsException(request.fromAccount());
        }

        Transaction transaction = new Transaction(request.amount(), request.type(), recipient, sender);
        transactionRepository.save(transaction);

        recipient.setBalance(newBalance);
        accountRepository.save(recipient);

        sender.setBalance(newBalance);
        accountRepository.save(sender);

        return TransactionsMapper.mapToDTO(transaction);
    }

    public TransactionDTO deposit(TransactionRequestDTO request) {
        final Account account = accountRepository.findByIdNumberAndIsClosedFalse(request.toAccount())
                .orElseThrow(() -> new AccountNotFoundException(request.toAccount()));

        BigDecimal newBalance = account.getBalance().add(request.amount());
        Transaction transaction = new Transaction(request.amount(), request.type(), account, null);
        transactionRepository.save(transaction);

        account.setBalance(newBalance);
        accountRepository.save(account);

        return TransactionsMapper.mapToDTO(transaction);
    }
}
