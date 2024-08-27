package com.jaqg.banking.repos;

import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindAllAccounts() {
        Account account1 = new Account();
        account1.setName("Savings");
        account1.setBalance(BigDecimal.TEN);
        account1.setSortCode(1234);

        Account account2 = new Account();
        account2.setName("Checking");
        account2.setBalance(BigDecimal.ONE);
        account2.setSortCode(1235);

        entityManager.persist(account1);
        entityManager.persist(account2);

        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts).hasSize(2).contains(account1, account2);
    }

    @Test
    void testFindAccountById() {
        Account account1 = new Account();
        account1.setName("Savings");
        account1.setBalance(BigDecimal.TEN);
        account1.setSortCode(1234);

        Account account2 = new Account();
        account2.setName("Checking");
        account2.setBalance(BigDecimal.ONE);
        account2.setSortCode(1235);

        entityManager.persist(account1);
        Account storedAccount = entityManager.persist(account2);

        Optional<Account> optionalAccount = accountRepository.findById(storedAccount.getNumber());
        assertThat(optionalAccount).isPresent();
        Account account = optionalAccount.get();
        assertThat(account.getNumber()).isEqualTo(storedAccount.getNumber());
        assertThat(account.getName()).isEqualTo(storedAccount.getName());
        assertThat(account.getBalance()).isEqualTo(storedAccount.getBalance());
        assertThat(account.getSortCode()).isEqualTo(storedAccount.getSortCode());
        assertThat(account.getTransactions()).isEmpty();
    }

    @Test
    void testSaveAccount() {
        final String expectedName = "Checking";
        final BigDecimal expectedBalance = BigDecimal.ZERO;

        Account account = new Account();
        account.setName("Savings");
        account.setBalance(BigDecimal.TEN);
        account.setSortCode(1234);

        final Account storedAccount = entityManager.persist(account);

        account.setName(expectedName);
        account.setBalance(expectedBalance);
        account = accountRepository.save(account);

        assertThat(account.getNumber()).isEqualTo(storedAccount.getNumber());
        assertThat(account.getName()).isEqualTo(expectedName);
        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }

}