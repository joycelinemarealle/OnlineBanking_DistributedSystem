package com.jaqg.banking.controllers;

import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Transaction;
import com.jaqg.banking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public List<Transaction> findAllTransactions() {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(
                1L,
                LocalDate.of(2024, 8, 23),
                new BigDecimal("250.75"),
                "Deposit",
                "George"
        ));
        return transactions;
    }
}
