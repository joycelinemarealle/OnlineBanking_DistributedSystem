package com.jaqg.banking.repos;

import com.jaqg.banking.entities.Account;
import java.util.List;

public interface AccountRepository {
    List<Account> getAllAccounts();
    Account findAccountByNumber(long number);
    void saveAccount(Account account);
    void batchInsert(List<Account> accounts);
}
