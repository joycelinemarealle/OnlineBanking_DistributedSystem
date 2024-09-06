package com.jaqg.banking.service.impl;

import com.jaqg.banking.dto.TransactionDTO;
import com.jaqg.banking.dto.TransactionRequestDTO;
import com.jaqg.banking.entity.Account;
import com.jaqg.banking.entity.RemoteAccount;
import com.jaqg.banking.entity.Transaction;
import com.jaqg.banking.exception.AccountNotFoundException;
import com.jaqg.banking.exception.RemoteTransactionException;
import com.jaqg.banking.mapper.TransactionsMapper;
import com.jaqg.banking.repository.LocalAccountRepository;
import com.jaqg.banking.repository.RemoteAccountRepository;
import com.jaqg.banking.repository.TransactionRepository;
import com.jaqg.banking.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final Integer sortCode;
    private final String remoteBankUrl;
    private final LocalAccountRepository accountRepository;
    private final RemoteAccountRepository remoteAccountRepository;
    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;

    public TransactionServiceImpl(LocalAccountRepository accountRepository,
                                  RemoteAccountRepository remoteAccountRepository,
                                  TransactionRepository transactionRepository,
                                  @Value("${sortcode}") Integer sortCode,
                                  @Value("${remote-bank-url}") String remoteBankUrl,
                                  RestTemplateBuilder restTemplateBuilder) {
        this.accountRepository = accountRepository;
        this.remoteAccountRepository = remoteAccountRepository;
        this.transactionRepository = transactionRepository;
        this.sortCode = sortCode;
        this.remoteBankUrl = remoteBankUrl;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public TransactionDTO withdraw(TransactionRequestDTO request) {
        Account account = subtractFromLocalAccount(request.fromAccount(), request.fromAccountSortCode(), request.amount());
        Transaction transaction = transactionRepository.save(new Transaction(request.amount(), request.type(), null, account));
        return TransactionsMapper.mapToDTO(transaction);
    }


    @Override
    public TransactionDTO deposit(TransactionRequestDTO request) {
        Account account = addToLocalAccount(request.toAccount(), request.toAccountSortCode(), request.amount());
        Transaction transaction = transactionRepository.save(new Transaction(request.amount(), request.type(), account, null));
        return TransactionsMapper.mapToDTO(transaction);
    }

    @Override
    public List<TransactionDTO> getAllTransactions(Long accountNumber) {
        final var account = accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(accountNumber, sortCode)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
        return TransactionsMapper.mapToDTO(account.getTransactions());
    }

    @Override
    public TransactionDTO transfer(TransactionRequestDTO request) {
        Account sender = processFromAccount(request);
        Account recipient = processToAccount(request);

        Transaction transaction = transactionRepository.save(new Transaction(request.amount(), request.type(), recipient, sender));

        if (isRemoteTransaction(request)) {
            remoteTransaction(request);
        }

        return TransactionsMapper.mapToDTO(transaction);
    }

    private Account processFromAccount(TransactionRequestDTO request) {
        if (Objects.equals(request.fromAccountSortCode(), sortCode)) {
            return subtractFromLocalAccount(request.fromAccount(), request.fromAccountSortCode(), request.amount());
        } else {
            return createRemoteAccount(request.fromAccount(), request.fromAccountSortCode());
        }
    }

    private Account processToAccount(TransactionRequestDTO request) {
        Integer accountSortCode = request.toAccountSortCode() == null || request.toAccountSortCode() == 0 ? sortCode : request.toAccountSortCode();
        if (Objects.equals(accountSortCode, sortCode)) {
            return addToLocalAccount(request.toAccount(), accountSortCode, request.amount());
        } else {
            return createRemoteAccount(request.toAccount(), accountSortCode);
        }
    }

    private Account subtractFromLocalAccount(Long accountNumber, Integer sortCode, BigDecimal amount) {
        final var account = accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(accountNumber, sortCode)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.subtractFromBalance(amount);
        return accountRepository.save(account);
    }

    private Account addToLocalAccount(Long accountNumber, Integer accountSortCode, BigDecimal amount) {
        final var account = accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(accountNumber, accountSortCode)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.addToBalance(amount);
        return accountRepository.save(account);
    }

    private Account createRemoteAccount(Long number, Integer sortCode) {
        return remoteAccountRepository.findByIdNumberAndIdSortCode(number, sortCode)
                .orElse(remoteAccountRepository.save(new RemoteAccount(number, sortCode)));
    }

    private boolean isRemoteTransaction(TransactionRequestDTO request) {
        Integer accountSortCode = request.toAccountSortCode() == null || request.toAccountSortCode() == 0 ? sortCode : request.toAccountSortCode();
        return  !Objects.equals(accountSortCode, sortCode);
    }

    private void remoteTransaction(TransactionRequestDTO request) {
        String url = remoteBankUrl + ":" + request.toAccountSortCode() + "/transaction";
        try {
            ResponseEntity<TransactionDTO> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request), TransactionDTO.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RemoteTransactionException("Could not complete remote transaction");
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RemoteTransactionException(e.getMessage(), e);
        }
    }
}
