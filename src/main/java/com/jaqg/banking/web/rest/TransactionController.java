package com.jaqg.banking.web.rest;

import com.jaqg.banking.dto.TransactionDTO;
import com.jaqg.banking.dto.TransactionRequestDTO;
import com.jaqg.banking.exception.IllegalRestArgumentException;
import com.jaqg.banking.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{accountNumber}")
    public List<TransactionDTO> retrieveAllCustomers(@PathVariable Long accountNumber) {
        return this.transactionService.getAllTransactions(accountNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO create(@RequestBody TransactionRequestDTO request) {
        if (request.amount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalRestArgumentException("Transaction amount must be greater than zero");
        }

        return switch (request.type()) {
            case WITHDRAWAL -> transactionService.withdraw(request);
            case DEPOSIT -> transactionService.deposit(request);
            case TRANSFER -> transactionService.transfer(request);
        };
    }

}
