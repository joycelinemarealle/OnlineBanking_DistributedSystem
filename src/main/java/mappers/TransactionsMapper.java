package mappers;

import com.jaqg.banking.entities.Transaction;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TransactionsMapper {

    //Convert List of Transaction Entity to List of TransactionResponseDTO
    public static List <com.jaqg.banking.dto.TransactionResponse>  transactionListMapper(List<Transaction> transactions){
       return  transactions.stream()
               .map(transaction -> transactionMapper(transaction)
               )
               .toList(); //collect to a list
    }

    public static com.jaqg.banking.dto.TransactionResponse transactionMapper(Transaction transaction){
        return new com.jaqg.banking.dto.TransactionResponse(
                transaction.getDateTime(),
                transaction.getTransType().toString(),
                transaction.getSender().getNumber(),
                transaction.getSender().getSortCode(),
                transaction.getRecipient().getNumber(),
                transaction.getRecipient().getSortCode(),
                transaction.getTransVal());

    }
}
