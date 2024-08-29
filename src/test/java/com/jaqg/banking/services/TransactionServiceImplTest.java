package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.OperationType;
import com.jaqg.banking.mapper.TransactionMapper;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionServiceImplTest {
    @Mock
    AccountRepository accountRepository;

    @Mock
    TransactionRepository transactionRepository;

    @Autowired
    TransactionMapper transactionMapper;

    @InjectMocks
    TransactionServiceImpl transactionServiceImpl;

    private TransactionRequest transferRequest;
    private TransactionRequest withdrawRequest;
    private TransactionRequest depositRequest;

    @BeforeEach
    void setup() {
        when(accountRepository.findById(any())).thenReturn(
                Optional.of(new Account(1234, "Savings", new BigDecimal(100), new BigDecimal(100), null, 4444))
        );
        transferRequest = new TransactionRequest(
                OperationType.TRANSFER,
                123456789L,
                1234L,
                987654321L,
                4444L,
                new BigDecimal(150.00));
        withdrawRequest = new TransactionRequest(
                OperationType.WITHDRAWAL,
                123456789L,
                1234L,
                null,
                null,
                new BigDecimal(100)
        );
        depositRequest = new TransactionRequest(
                OperationType.DEPOSIT,
                null,
                null,
                123456789L,
                1234L,
                new BigDecimal(100 )
        );
    }


    @Test
    void transfer() {
        TransactionResponse response = transactionServiceImpl.executeTransfer(transferRequest);
        assertEquals(response.amount(), transferRequest.amount());
        verify(accountRepository.findById(transferRequest.toAccount()));
        verify(accountRepository.save(any()));
    }


    @Test
    void withdraw() {
        TransactionResponse response = transactionServiceImpl.withdraw(withdrawRequest);
        assertEquals(response.amount(), withdrawRequest.amount());
            verify(accountRepository.findById(withdrawRequest.fromAccount()));
        verify(accountRepository.save(any()));
    }


    @Test
    void deposit() {
    }

    @Test
    void getAllTransactions() {
    }
}