package com.jaqg.banking.repos;

import com.jaqg.banking.entities.Account;
import com.jaqg.banking.enums.OperationType;
import com.jaqg.banking.entities.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class TransactionRepositoryTest {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TestEntityManager entityManager;


    @ParameterizedTest
    @EnumSource(OperationType.class)
    void testOperationType(OperationType type) {
        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setDateTime(LocalDateTime.now());
        expectedTransaction.setTransType(type);
        expectedTransaction.setTransVal(BigDecimal.TEN);

        final long id = entityManager.persist(expectedTransaction).getId();

        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        assertThat(transactionOptional).isPresent();
        Transaction transaction = transactionOptional.get();
        assertEquals(type, transaction.getTransType());
    }

    @Test
    void testFindAllTransactions() {
        Transaction transaction1 = new Transaction();
        transaction1.setRecipient(new Account());
        transaction1.setTransType(OperationType.DEPOSIT);
        transaction1.setTransVal(BigDecimal.TEN);
        transaction1.setDateTime(LocalDateTime.of(2024, 6, 2, 23, 34, 34));

        Transaction transaction2 = new Transaction();
        transaction1.setRecipient(new Account());
        transaction1.setTransType(OperationType.WITHDRAWAL);
        transaction1.setTransVal(BigDecimal.ONE);
        transaction2.setDateTime(LocalDateTime.now());

        entityManager.persist(transaction1);
        entityManager.persist(transaction2);

        List<Transaction> Transactions = transactionRepository.findAll();
        assertThat(Transactions).hasSize(2).contains(transaction1, transaction2);
    }

    @Test
    void testFindTransactionById() {
        Transaction transaction1 = new Transaction();
        transaction1.setRecipient(new Account());
        transaction1.setTransType(OperationType.DEPOSIT);
        transaction1.setTransVal(BigDecimal.TEN);
        transaction1.setDateTime(LocalDateTime.of(2024, 6, 2, 23, 34, 34));

        Transaction transaction2 = new Transaction();
        transaction1.setRecipient(new Account());
        transaction1.setTransType(OperationType.WITHDRAWAL);
        transaction1.setTransVal(BigDecimal.ONE);
        transaction2.setDateTime(LocalDateTime.now());

        entityManager.persist(transaction1);
        Transaction storedTransaction = entityManager.persist(transaction2);

        Optional<Transaction> optionalTransaction = transactionRepository.findById(storedTransaction.getId());
        assertThat(optionalTransaction).isPresent();
        Transaction transaction = optionalTransaction.get();
        assertThat(transaction.getId()).isEqualTo(storedTransaction.getId());
        assertThat(transaction.getDateTime()).isEqualTo(storedTransaction.getDateTime());
        assertThat(transaction.getTransVal()).isEqualTo(storedTransaction.getTransVal());
        assertThat(transaction.getTransType()).isEqualTo(storedTransaction.getTransType());
    }

    @Test
    void testSaveTransaction() {
        final OperationType expectedType = OperationType.TRANSFER;
        final BigDecimal expectedValue = BigDecimal.ONE;

        Transaction transaction = new Transaction();
        transaction.setRecipient(new Account());
        transaction.setTransType(OperationType.DEPOSIT);
        transaction.setTransVal(BigDecimal.TEN);
        transaction.setDateTime(LocalDateTime.of(2024, 6, 2, 23, 34, 34));

        final Transaction storedTransaction = entityManager.persist(transaction);

        transaction.setTransType(expectedType);
        transaction.setTransVal(expectedValue);
        transaction = transactionRepository.save(transaction);

        assertThat(transaction.getId()).isEqualTo(storedTransaction.getId());
        assertThat(transaction.getTransType()).isEqualTo(expectedType);
        assertThat(transaction.getTransVal()).isEqualTo(expectedValue);
    }
}