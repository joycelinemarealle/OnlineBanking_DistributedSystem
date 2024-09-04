package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.AccountDTO;
import com.jaqg.banking.dto.AccountRequestDTO;
import com.jaqg.banking.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {
    private final AccountService accountService;

    //constructor injection
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        return accountService.retrieveAllAccounts();
    }

    @GetMapping("/{number}")
    public ResponseEntity<AccountDTO> findAccountByNumber(@PathVariable Long number) {
        AccountDTO accountDTO = accountService.findAccountByNumber(number);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountRequestDTO createAccountDTO) {
        AccountDTO accountDTO = accountService.createAccount(createAccountDTO);
        return new ResponseEntity<>(accountDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<BigDecimal> closeAccount(@PathVariable long number) {
        BigDecimal balance = accountService.closeAccount(number);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

}

