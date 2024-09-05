package com.jaqg.banking.service;

import com.jaqg.banking.dto.TransactionDTO;
import com.jaqg.banking.dto.TransactionRequestDTO;
import com.jaqg.banking.entity.LocalAccount;
import com.jaqg.banking.enums.TransactionType;
import com.jaqg.banking.repository.LocalAccountRepository;
import com.jaqg.banking.repository.TransactionRepository;
import com.jaqg.banking.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Disabled
class TransactionServiceImplTest {
    @Mock
    LocalAccountRepository accountRepository;

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionServiceImpl transactionServiceImpl;

    private TransactionRequestDTO transferRequest;
    private TransactionRequestDTO withdrawRequest;
    private TransactionRequestDTO depositRequest;

    @BeforeEach
    void setup() {
        when(accountRepository.findById(any())).thenReturn(
                Optional.of(new LocalAccount("Savings", new BigDecimal("100"), null, 4444))
        );
        transferRequest = new TransactionRequestDTO(
                TransactionType.TRANSFER,
                123456789L,
                1234,
                987654321L,
                4444,
                new BigDecimal("150.00"));
        withdrawRequest = new TransactionRequestDTO(
                TransactionType.WITHDRAWAL,
                123456789L,
                1234,
                null,
                null,
                new BigDecimal(100)
        );
        depositRequest = new TransactionRequestDTO(
                TransactionType.DEPOSIT,
                null,
                null,
                123456789L,
                1234,
                new BigDecimal(100)
        );
    }


    @Test
    void transfer() {
        TransactionDTO response = transactionServiceImpl.transfer(transferRequest);
        assertEquals(response.amount(), transferRequest.amount());
        verify(accountRepository.findById(transferRequest.toAccount()));
        verify(accountRepository.save(any()));
    }


    @Test
    void withdraw() {
        TransactionDTO response = transactionServiceImpl.withdraw(withdrawRequest);
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