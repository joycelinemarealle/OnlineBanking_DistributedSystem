package com.jaqg.banking.mapper;

import com.jaqg.banking.dto.TransactionResponse;
import com.jaqg.banking.enums.OperationType;
import com.jaqg.banking.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionResponse transactionToTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getDateTime(),
                OperationType.TRANSFER,
                transaction.getRecipient().getNumber(),
                transaction.getRecipient().getSortCode(),
                transaction.getSender().getNumber(),//sender
                transaction.getSender().getSortCode(),//sender
                transaction.getTransVal());

    }
}
