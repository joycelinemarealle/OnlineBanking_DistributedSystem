package com.jaqg.banking.services;

import com.jaqg.banking.entities.Account;
import com.jaqg.banking.repos.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocalAccountService implements AccountService{

    private final AccountRepository accountRepository;
    private final Logger logger = LoggerFactory.getLogger(LocalAccountService.class);

    //inject repo
    @Autowired
    public LocalAccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;

    }
    @Override
    public List<Account> retrieveAllAccounts() {
        logger.info("Getting accounts from Account Repository");

        return accountRepository.findAll();
    }
}
