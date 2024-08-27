package mappers;

import com.jaqg.banking.entities.Transaction;

import java.util.List;
import com.jaqg.banking.dto.TransactionResponse;
public class TransactionsMapper {

    //Convert List of Transaction Entity to List of TransactionResponseDTO

    public static List <TransactionResponse>  transactionMapper(List<Transaction> transactions){
       return  transactions.stream()
               .map(transaction -> new com.jaqg.banking.dto.TransactionResponse(
                       transaction.getDateTime(),
                       transaction.getTransType(),
                       transaction.getSender().getNumber(),
                       transaction.getSender().getSortCode(),
                       transaction.getRecipient().getNumber(),
                       transaction.getRecipient().getSortCode(),
                       transaction.getTransVal()
               ))
               .toList(); //collect to a list
    }

}
