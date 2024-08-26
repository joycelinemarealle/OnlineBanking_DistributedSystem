package com.jaqg.banking.controller;

import com.jaqg.banking.entities.Account;
import com.jaqg.banking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/accounts")
@CrossOrigin

public class AccountController {

    //inject service
    @Autowired
    AccountService accountService;

    @GetMapping
    List<Account> getAllAccounts(){
        return accountService.retrieveAllAccounts();
    }

    @PostMapping
    public void statusResponse(Account account){
        accountService.retrieveAllAccounts().add(account);
    }

}
