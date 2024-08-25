package com.jaqg.banking.repos;

import com.jaqg.banking.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> getAllAccounts();
    Account findAccountByNumber(long number);
    void saveAccount(Account account);
    void batchInsert(List<Account> accounts);
}
