package com.jaqg.banking.mapper;

import com.jaqg.banking.dto.AccountDTO;
import com.jaqg.banking.entities.Account;

import java.util.List;

//Convert Account Entity to AccountResponseDTO
public class AccountMapper {

    public static List<AccountDTO> accountMapper(List<Account> accounts) {
        return accounts.stream()
                .map(AccountMapper::accountMapper)
                .toList();
    }

    public static AccountDTO accountMapper(Account account) {
        return new AccountDTO(
                account.getNumber(),
                account.getSortCode(),
                account.getName(),
                account.getOpeningBalance(),
                TransactionsMapper.mapToDTO(account.getTransactions()), // convert to DTO,
                account.getBalance(),
                account.getCustomer().getId());
    }
}
