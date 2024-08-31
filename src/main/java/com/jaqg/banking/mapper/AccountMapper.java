package com.jaqg.banking.mapper;

import com.jaqg.banking.dto.AccountDTO;
import com.jaqg.banking.entities.Account;

import static com.jaqg.banking.mapper.TransactionsMapper.transactionListMapper;

//Convert Account Entity to AccountResponseDTO
public class AccountMapper {
    public static AccountDTO accountMapper(Account account) {
        return new AccountDTO(
                account.getNumber(),
                account.getSortCode(),
                account.getName(),
                account.getOpeningBalance(),
                transactionListMapper(account.getTransactions()), // convert to DTO,
                account.getBalance(),
                account.getCustomer().getId());
    }
}
