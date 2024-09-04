package com.jaqg.banking.services;

import com.jaqg.banking.dto.TransactionDTO;
import com.jaqg.banking.dto.TransactionRequestDTO;

public interface TransactionService {

    TransactionDTO withdraw(TransactionRequestDTO request);

    TransactionDTO executeTransfer(TransactionRequestDTO request);

    TransactionDTO deposit(TransactionRequestDTO request);

}
