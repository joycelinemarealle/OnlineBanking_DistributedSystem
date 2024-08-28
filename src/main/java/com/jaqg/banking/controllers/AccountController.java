package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.AccountResponseDTO;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts(){
        List<AccountResponseDTO> accountResponseDTOList = accountService.retrieveAllAccounts();
        return new ResponseEntity<>(accountResponseDTOList, HttpStatus.OK);
    }

    @GetMapping("/{number}")
        public ResponseEntity< AccountResponseDTO> findAccountByNumber(@PathVariable Long number){
        AccountResponseDTO accountResponseDTO =accountService.findAccountByNumber(number);
            return  new ResponseEntity<>(accountResponseDTO, HttpStatus.OK);
        }

    @PostMapping("/account")
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody Account account){
        Customer customerId = account.getCustomer();
        String name = account.getName();
        BigDecimal openingBalance = account.getOpeningBalance();
        AccountResponseDTO accountResponseDTO = accountService.createAccount(customerId, name, openingBalance);
       return new ResponseEntity<>(accountResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<BigDecimal> closeAccount(@PathVariable long number) throws AccountNotFoundException {
      BigDecimal balance = accountService.closeAccount(number);
        return new ResponseEntity<>( balance, HttpStatus.OK);
    }

}

