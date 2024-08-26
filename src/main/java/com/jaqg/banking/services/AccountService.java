package com.jaqg.banking.services;

import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountService {
    List<Account> retrieveAllAccounts();
    Account createAccount(Customer customer, String name, BigDecimal openingBalance);
    BigDecimal closeAccount(long number) throws AccountNotFoundException;
    Account findAccountByNumber(long number);

}
