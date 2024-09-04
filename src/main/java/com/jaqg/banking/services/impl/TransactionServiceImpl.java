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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Value("${sortcode}")
    private Integer sortcode;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, RestTemplateBuilder restTemplateBuilder) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public TransactionDTO withdraw(TransactionRequestDTO request) {
        final Account account = accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(request.fromAccount(), request.fromAccountSortCode())
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
        final Optional<Account> senderOptional = accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(request.fromAccount(), request.fromAccountSortCode());
        final Optional<Account> recipientOptional = accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(request.toAccount(), request.toAccountSortCode());

        Account sender;
        BigDecimal newBalance = BigDecimal.ZERO;
        if (Objects.equals(request.fromAccountSortCode(), sortcode)) {
            sender = senderOptional.orElseThrow(() -> new AccountNotFoundException(request.fromAccount()));
            newBalance = sender.getBalance().subtract(request.amount());
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new NotEnoughFundsException(request.fromAccount());
            }
        } else {
            //logic of remote transfer
            sender = senderOptional.orElse(new Account("remote", request.amount(), null, request.fromAccountSortCode()));
        }

        Account recipient;
        if (Objects.equals(request.toAccountSortCode(), sortcode)) {
            recipient = recipientOptional.orElseThrow(() -> new AccountNotFoundException(request.fromAccount()));
        } else {
            //logic of remote transfer
            String url = "http://localhost:" + request.toAccountSortCode() + "/transaction";
            TransactionDTO response = restTemplate.postForObject(url, request, TransactionDTO.class);
            recipient = recipientOptional.orElse(new Account(request.toAccount(), "remote", BigDecimal.ZERO, null, request.toAccountSortCode()));
        }

        recipient.setBalance(recipient.getBalance().add(request.amount()));
        recipient = accountRepository.save(recipient);

        sender.setBalance(newBalance);
        sender = accountRepository.save(sender);

        Transaction transaction = new Transaction(request.amount(), request.type(), recipient, sender);
        transactionRepository.save(transaction);

        return TransactionsMapper.mapToDTO(transaction);
    }

    public TransactionDTO deposit(TransactionRequestDTO request) {
        final Account account = accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(request.toAccount(), request.toAccountSortCode())
                .orElseThrow(() -> new AccountNotFoundException(request.toAccount()));

        BigDecimal newBalance = account.getBalance().add(request.amount());
        Transaction transaction = new Transaction(request.amount(), request.type(), account, null);
        transactionRepository.save(transaction);

        account.setBalance(newBalance);
        accountRepository.save(account);

        return TransactionsMapper.mapToDTO(transaction);
    }
}
