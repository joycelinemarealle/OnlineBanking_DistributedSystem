package mappers;

import com.jaqg.banking.dto.AccountResponseDTO;
import com.jaqg.banking.entities.Account;

import static mappers.TransactionsMapper.transactionListMapper;

//Convert Account Entity to AccountResponseDTO
public class AccountMapper {
    public static AccountResponseDTO accountMapper(Account account) {
        return new AccountResponseDTO(
                account.getNumber(),
                account.getSortCode(),
                account.getName(),
                account.getOpeningBalance(),
                transactionListMapper(account.getTransactions()), // convert to DTO,
                account.getBalance(),
                account.getCustomer().getUniqueID());

    }
}
