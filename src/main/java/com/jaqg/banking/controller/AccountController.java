package com.jaqg.banking.controller;

import com.jaqg.banking.entities.Account;
import com.jaqg.banking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;
@RestController
@RequestMapping("/account")
@CrossOrigin

public class AccountController {

    //inject service
    @Autowired
    AccountService accountService;

    @GetMapping("/account")
    public List<Account> getAllAccounts(){
        return accountService.retrieveAllAccounts();
    }

    @GetMapping("/account/{number}")
        public Account findAccountByNumber(@PathVariable Long number){
            return accountService.findAccountByNumber(number);
        }

    @PostMapping("/account")
    public Account createAccount(@RequestBody Account account){
       return accountService.createAccount();
    }

    @DeleteMapping("accounts/{number}")
    public BigDecimal closeAccount(@PathVariable long number) throws AccountNotFoundException {
      return  accountService.closeAccount(number);
    }

}

