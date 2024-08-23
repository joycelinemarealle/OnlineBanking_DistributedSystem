package com.jaqg.banking.services;

import com.jaqg.banking.entities.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<Account> retrieveAllAccounts();
}
