package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.entities.Transaction;
import com.jaqg.banking.services.TransactionService;
import com.jaqg.banking.services.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionServiceImpl transactionService;

    @GetMapping
    public List<TransactionResponse> retrieveAllCustomers(){
        return this.transactionService.getAllTransactions();
    }

    @PostMapping
    public TransactionResponse create(@RequestBody TransactionRequest request){
//        LocalDateTime time,
//        String type,
//        Long fromAccount,
//        Integer fromAccountSortCode,
//        Long toAccount,
//        Integer toAccountSortCode,
//        BigDecimal amount
        return new TransactionResponse(
                LocalDateTime.now(),
                request.type(),
                request.fromAcount(),
                request.fromAcountSortCode().intValue(),
                request.toAcount(),
                request.toAcountSortCode().intValue(),
                request.amount()
        );
//        switch(request.type()) {
//            case "WITHDRAW":
//                return transactionService.withdraw(request);
//            case "DEPOSIT":
//                return transactionService.deposit(request);
//            case "TRANSFER":
//                return transactionService.executeTransfer(request);
//            default:
//                throw new IllegalArgumentException("Invalid transaction type" + request.type());
//        }
    }
    //app.post('/account', (res, req) => {
    //    if(req.body.type == 'withdraw') {
    //        // something
    //    }
    //    res.status = 200;
    //    res.body = {message:'everything succeeded'};
    //    return res;
    //})
}
