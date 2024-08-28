package mappers;

import com.jaqg.banking.entities.Transaction;

import java.util.List;
import com.jaqg.banking.dto.TransactionResponse;

import static java.util.stream.Collectors.toList;

public class TransactionsMapper {

    //Convert List of Transaction Entity to List of TransactionResponseDTO
    public static List <TransactionResponse>  transactionListMapper(List<Transaction> transactions){
       return  transactions.stream()
               .map(transaction -> transactionMapper(transaction)
               )
               .toList(); //collect to a list
    }

    public static TransactionResponse transactionMapper(Transaction transaction){
        return new TransactionResponse(
                transaction.getDateTime(),
                transaction.getTransType(),
                transaction.getSender().getNumber(),
                transaction.getSender().getSortCode(),
                transaction.getRecipient().getNumber(),
                transaction.getRecipient().getSortCode(),
                transaction.getTransVal());

    }
}
