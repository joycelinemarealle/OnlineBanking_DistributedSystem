package com.jaqg.banking.services.impl;

import com.jaqg.banking.dto.AccountDTO;
import com.jaqg.banking.dto.AccountRequestDTO;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.exceptions.AccountNotFoundException;
import com.jaqg.banking.exceptions.CustomerNotFoundException;
import com.jaqg.banking.exceptions.NotEnoughFundsException;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.CustomerRepository;
import com.jaqg.banking.services.AccountService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.jaqg.banking.mapper.AccountMapper.accountMapper;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final Integer sortCode;

    public AccountServiceImpl(
            AccountRepository accountRepository,
            CustomerRepository customerRepository,
            @Value("${sortcode}") Integer sortCode
    ) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.sortCode = sortCode;
    }

    @Override
    public List<AccountDTO> retrieveAllAccounts() {
        logger.info("Getting accounts from Account Repository");

        //Retrieve all accounts from the repository
        List<Account> accounts = accountRepository.findByIsClosedFalse();

        //Convert each Account Entity to a AccountResponseDTO
        return accountMapper(accounts);
    }

    @Override
    public AccountDTO createAccount(AccountRequestDTO accountRequestDTO) throws AccountNotFoundException {

        //Validate opening balance
        if (accountRequestDTO.openingBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughFundsException("Opening balance cannot be negative ");
        }

        //find customer by id then create Account
        Customer customer = customerRepository.findByIdAndIsRemovedFalse(accountRequestDTO.customerId())
                .orElseThrow(() -> new CustomerNotFoundException(accountRequestDTO.customerId()));

        //save account to database
        Account account = accountRepository.save(
                new Account(accountRequestDTO.accountName(), accountRequestDTO.openingBalance(), customer, sortCode)
        );
        return accountMapper(account);
    }

    @Override
    public BigDecimal closeAccount(long number) throws AccountNotFoundException {
        Account account = accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(number, sortCode)
                .orElseThrow(() -> new AccountNotFoundException(number));
        BigDecimal balance = account.getBalance();
        account.setBalance(BigDecimal.ZERO);
        account.setClosed(true);
        accountRepository.save(account);
        return balance;
    }

    @Override
    public AccountDTO findAccountByNumber(long number) throws AccountNotFoundException {
        Account account = accountRepository.findByIdNumberAndIdSortCodeAndIsClosedFalse(number, sortCode)
                .orElseThrow(() -> new AccountNotFoundException(number));
        return accountMapper(account);
    }

}
