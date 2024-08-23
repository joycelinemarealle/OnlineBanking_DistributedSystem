package com.jaqg.banking.repos;

import com.jaqg.banking.entities.Account;

import java.util.List;

public interface CustomerRepo {
    List<Account> retrieveAllAccounts();

    Account findAccountById(long ID);






}
