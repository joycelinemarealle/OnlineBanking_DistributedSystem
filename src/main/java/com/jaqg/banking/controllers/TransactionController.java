package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.TransactionDTO;
import com.jaqg.banking.dto.TransactionRequestDTO;
import com.jaqg.banking.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO create(@RequestBody TransactionRequestDTO request) {
        return switch (request.type()) {
            case WITHDRAWAL -> transactionService.withdraw(request);
            case DEPOSIT -> transactionService.deposit(request);
            case TRANSFER -> transactionService.executeTransfer(request);
        };
    }

}
