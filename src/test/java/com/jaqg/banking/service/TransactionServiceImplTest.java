package com.jaqg.banking.service;

import com.jaqg.banking.dto.TransactionDTO;
import com.jaqg.banking.dto.TransactionRequestDTO;
import com.jaqg.banking.entity.LocalAccount;
import com.jaqg.banking.entity.Transaction;
import com.jaqg.banking.enums.TransactionType;
import com.jaqg.banking.exception.NotEnoughFundsException;
import com.jaqg.banking.repository.LocalAccountRepository;
import com.jaqg.banking.repository.RemoteAccountRepository;
import com.jaqg.banking.repository.TransactionRepository;
import com.jaqg.banking.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    private final static Integer SORT_CODE = 1234;

    @Mock
    private LocalAccountRepository accountRepository;

    @Mock
    private RemoteAccountRepository remoteAccountRepository;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TransactionRepository transactionRepository;

    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        transactionService = new TransactionServiceImpl(accountRepository, remoteAccountRepository, transactionRepository, SORT_CODE, restTemplateBuilder);
    }

    @Test
    void testDeposit() {
        final var request = new TransactionRequestDTO(
                TransactionType.DEPOSIT,
                null,
                null,
                123456789L,
                SORT_CODE,
                new BigDecimal("100")
        );

        LocalAccount account = new LocalAccount("Savings", new BigDecimal("100"), null, SORT_CODE);
        account.setNumber(request.toAccount());
        Transaction transaction = new Transaction(request.amount(), request.type(), account, null);

        when(accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(request.toAccount(), request.toAccountSortCode())).thenReturn(Optional.of(account));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(accountRepository.save(account)).thenReturn(account);

        TransactionDTO response = transactionService.deposit(request);

        assertEquals(request.toAccountSortCode(), response.toAccountSortCode());
        assertEquals(request.toAccount(), response.toAccount());
        assertEquals(request.fromAccount(), response.fromAccount());
        assertEquals(request.fromAccountSortCode(), response.fromAccountSortCode());
        assertEquals(request.type(), response.type());
        assertEquals(request.amount(), response.amount());
        assertEquals(account.getBalance(), new BigDecimal("200"));

        verify(accountRepository).findByIdNumberAndIdSortCodeAndIsClosedFalse(request.toAccount(), request.toAccountSortCode());
        verify(transactionRepository).save(any(Transaction.class));
        verify(accountRepository).save(account);
    }

    @Test
    void testWithdraw() {
        final var request = new TransactionRequestDTO(
                TransactionType.WITHDRAWAL,
                123456789L,
                SORT_CODE,
                null,
                null,
                new BigDecimal("20")
        );

        LocalAccount account = new LocalAccount("Savings", new BigDecimal("100"), null, SORT_CODE);
        account.setNumber(request.fromAccount());
        Transaction transaction = new Transaction(request.amount(), request.type(), null, account);

        when(accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(request.fromAccount(), request.fromAccountSortCode())).thenReturn(Optional.of(account));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(accountRepository.save(account)).thenReturn(account);

        TransactionDTO response = transactionService.withdraw(request);

        assertEquals(request.toAccountSortCode(), response.toAccountSortCode());
        assertEquals(request.toAccount(), response.toAccount());
        assertEquals(request.fromAccount(), response.fromAccount());
        assertEquals(request.fromAccountSortCode(), response.fromAccountSortCode());
        assertEquals(request.type(), response.type());
        assertEquals(request.amount(), response.amount());
        assertEquals(account.getBalance(), new BigDecimal("80"));

        verify(accountRepository).findByIdNumberAndIdSortCodeAndIsClosedFalse(request.fromAccount(), request.fromAccountSortCode());
        verify(transactionRepository).save(any(Transaction.class));
        verify(accountRepository).save(account);
    }

    @Test
    void testWithdrawWhenNotEnoughFunds() {
        final var request = new TransactionRequestDTO(
                TransactionType.WITHDRAWAL,
                123456789L,
                SORT_CODE,
                null,
                null,
                new BigDecimal("110")
        );

        LocalAccount account = new LocalAccount("Savings", new BigDecimal("100"), null, SORT_CODE);
        account.setNumber(request.fromAccount());

        when(accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(request.fromAccount(), request.fromAccountSortCode())).thenReturn(Optional.of(account));

        assertThrows(NotEnoughFundsException.class, () -> transactionService.withdraw(request));

        verify(accountRepository).findByIdNumberAndIdSortCodeAndIsClosedFalse(request.fromAccount(), request.fromAccountSortCode());
    }

    @Test
    void testTransfer() {
        final var request = new TransactionRequestDTO(
                TransactionType.TRANSFER,
                123456789L,
                SORT_CODE,
                987654321L,
                SORT_CODE,
                new BigDecimal("100")
        );

        LocalAccount sender = new LocalAccount("Savings", new BigDecimal("100"), null, request.fromAccountSortCode());
        sender.setNumber(request.fromAccount());

        LocalAccount recipient = new LocalAccount("Checking", new BigDecimal("100"), null, request.toAccountSortCode());
        recipient.setNumber(request.toAccount());

        Transaction transaction = new Transaction(request.amount(), request.type(), recipient, sender);

        when(accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(request.toAccount(), request.toAccountSortCode())).thenReturn(Optional.of(recipient));
        when(accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(request.fromAccount(), request.fromAccountSortCode())).thenReturn(Optional.of(sender));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(accountRepository.save(sender)).thenReturn(sender);
        when(accountRepository.save(recipient)).thenReturn(recipient);

        TransactionDTO response = transactionService.transfer(request);

        assertEquals(request.toAccountSortCode(), response.toAccountSortCode());
        assertEquals(request.toAccount(), response.toAccount());
        assertEquals(request.fromAccount(), response.fromAccount());
        assertEquals(request.fromAccountSortCode(), response.fromAccountSortCode());
        assertEquals(request.type(), response.type());
        assertEquals(request.amount(), response.amount());
        assertEquals(sender.getBalance(), BigDecimal.ZERO);
        assertEquals(recipient.getBalance(), new BigDecimal("200"));

        verify(accountRepository).findByIdNumberAndIdSortCodeAndIsClosedFalse(request.toAccount(), request.toAccountSortCode());
        verify(accountRepository).findByIdNumberAndIdSortCodeAndIsClosedFalse(request.fromAccount(), request.fromAccountSortCode());
        verify(transactionRepository).save(any(Transaction.class));
        verify(accountRepository).save(sender);
        verify(accountRepository).save(recipient);
    }

    @Test
    void testTransferWhenNotEnoughFunds() {
        final var request = new TransactionRequestDTO(
                TransactionType.TRANSFER,
                123456789L,
                SORT_CODE,
                987654321L,
                SORT_CODE,
                new BigDecimal("110")
        );

        LocalAccount sender = new LocalAccount("Savings", new BigDecimal("100"), null, request.fromAccountSortCode());
        sender.setNumber(request.fromAccount());

        when(accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(request.fromAccount(), request.fromAccountSortCode())).thenReturn(Optional.of(sender));

        assertThrows(NotEnoughFundsException.class, () -> transactionService.transfer(request));

        verify(accountRepository).findByIdNumberAndIdSortCodeAndIsClosedFalse(request.fromAccount(), request.fromAccountSortCode());
    }
}