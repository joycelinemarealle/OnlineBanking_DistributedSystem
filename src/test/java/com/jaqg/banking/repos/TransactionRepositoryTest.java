package com.jaqg.banking.repos;

import com.jaqg.banking.entities.OperationType;
import com.jaqg.banking.entities.Transaction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        expectedTransaction.setDateTime(LocalDate.now());
        expectedTransaction.setTransType(type);
        expectedTransaction.setTransVal(BigDecimal.TEN);

        final long id = entityManager.persist(expectedTransaction).getId();

        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        assertThat(transactionOptional).isPresent();
        Transaction transaction = transactionOptional.get();
        assertEquals(type, transaction.getTransType());
    }
}