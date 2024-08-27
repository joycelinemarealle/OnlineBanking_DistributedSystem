package com.jaqg.banking.repos;

import com.jaqg.banking.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByNumber(long number);
    void batchInsert(List<Account> accounts);
}
