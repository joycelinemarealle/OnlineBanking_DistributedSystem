package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.AccountResponseDTO;
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

    @GetMapping
    public List<AccountResponseDTO> getAllAccounts(){
        return accountService.retrieveAllAccounts();
    }

    @GetMapping("/{number}")
        public AccountResponseDTO findAccountByNumber(@PathVariable Long number){
            return accountService.findAccountByNumber(number);
        }

//    @PostMapping("/account")
//    public Account createAccount(@RequestBody Account account){
//       return accountService.createAccount();
//    }

    @DeleteMapping("/{number}")
    public BigDecimal closeAccount(@PathVariable long number) throws AccountNotFoundException {
      return  accountService.closeAccount(number);
    }

}

