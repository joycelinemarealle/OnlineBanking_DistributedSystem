package com.jaqg.banking.mapper;

import com.jaqg.banking.dto.TransactionDTO;
import com.jaqg.banking.entities.Transaction;

import java.util.List;

public class TransactionsMapper {

    //Convert List of Transaction Entity to List of TransactionResponseDTO
    public static List<TransactionDTO> transactionListMapper(List<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionsMapper::transactionMapper)
                .toList(); //collect to a list
    }

    public static TransactionDTO transactionMapper(Transaction transaction) {
        return new TransactionDTO(
                transaction.getDateTime(),
                transaction.getTransType(),
                transaction.getFromAccountNumber(),
                transaction.getFromAccountSourceCode(),
                transaction.getToAccountNumber(),
                transaction.getToAccountSourceCode(),
                transaction.getTransVal());

    }
}
