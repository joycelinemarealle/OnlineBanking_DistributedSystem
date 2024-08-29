package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.AccountResponseDTO;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.repos.CustomerRepo;
import com.jaqg.banking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jaqg.banking.dto.CreateAccountRequestDTO;
import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;
@RestController
@RequestMapping("/account")
@CrossOrigin

public class AccountController {
    private AccountService accountService;

    //constructor injection
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts(){
        List<AccountResponseDTO> accountResponseDTOList = accountService.retrieveAllAccounts();
        return new ResponseEntity<>(accountResponseDTOList, HttpStatus.OK);
    }

    @GetMapping("/{number}")
        public ResponseEntity< AccountResponseDTO> findAccountByNumber(@PathVariable Long number){
        AccountResponseDTO accountResponseDTO =accountService.findAccountByNumber(number);
            return  new ResponseEntity<>(accountResponseDTO, HttpStatus.OK);
        }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody CreateAccountRequestDTO createAccountDTO){
        AccountResponseDTO accountResponseDTO = accountService.createAccount(createAccountDTO );
       return new ResponseEntity<>(accountResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<BigDecimal> closeAccount(@PathVariable long number) throws AccountNotFoundException {
      BigDecimal balance = accountService.closeAccount(number);
        return new ResponseEntity<>( balance, HttpStatus.OK);
    }

}

