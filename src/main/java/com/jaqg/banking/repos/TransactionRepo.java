package com.jaqg.banking.repos;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Account> findTransactionById();
    Account getAccount(int fromAccount, int fromAccountSortCode);
    void save(Account account);
}