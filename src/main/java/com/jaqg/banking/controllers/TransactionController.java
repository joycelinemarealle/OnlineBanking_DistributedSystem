package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;
import com.jaqg.banking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public List<TransactionResponse> retrieveAllCustomers(){
        return this.transactionService.getAllTransactions();
    }

    @PostMapping
    public TransactionResponse create(@RequestBody TransactionRequest request){
        return switch (request.type()) {
            case WITHDRAWAL -> transactionService.withdraw(request);
            case DEPOSIT -> transactionService.deposit(request);
            case TRANSFER -> transactionService.executeTransfer(request);
        };
    }
    //app.post('/account', (res, req) => {
    //    if(req.body.type == 'withdraw') {
    //        // something
    //    }
    //    res.status = 200;
    //    res.body = {message:'everything succeeded'};
    //    return res;
    //}) Express endpoint example
}
