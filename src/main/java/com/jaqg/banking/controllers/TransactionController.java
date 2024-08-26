package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Transaction;
import com.jaqg.banking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    //app.post('/account', (res, req) => {
    //    if(req.body.type == 'withdraw') {
    //        // something
    //    }
    //    res.status = 200;
    //    res.body = {message:'everything succeeded'};
    //    return res;
    //})
    @PostMapping
    public TransactionResponse create(@RequestBody TransactionRequest request){
        switch(request.getType()) {
            case "WITHDRAW":
                return transactionService.withdraw(request);
            break;
            case "DEPOSIT":
                break;
            case "TRANSFER":
                return transactionService.executeTransfer(request);
            break;
        }
    }
}
