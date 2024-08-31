package com.jaqg.banking.services;

import com.jaqg.banking.dto.AccountDTO;
import com.jaqg.banking.dto.AccountRequestDTO;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.exceptions.AccountNotFoundException;
import com.jaqg.banking.mapper.AccountMapper;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.jaqg.banking.mapper.AccountMapper.accountMapper;

@Service
@Transactional
public class LocalAccountService implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepo customerRepo;
    private final Logger logger = LoggerFactory.getLogger(LocalAccountService.class);

    @Value("${sortcode}")
    private Integer sortcode;

    //inject repo
    public LocalAccountService(AccountRepository accountRepository, CustomerRepo customerRepo) {
        this.accountRepository = accountRepository;
        this.customerRepo = customerRepo;

    }

    @Override
    public List<AccountDTO> retrieveAllAccounts() {
        logger.info("Getting accounts from Account Repository");

        //Retrieve all accounts from the repository
        List<Account> accounts = accountRepository.findAll();

        //Convert each Account Entity to a AccountResponseDTO
        return accounts.stream()
                .map(AccountMapper::accountMapper)
                .toList(); //collect to a list
    }

    @Override
    public AccountDTO createAccount(AccountRequestDTO accountRequestDTO) throws AccountNotFoundException {

        //Validate opening balance
        if (accountRequestDTO.openingBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative ");
        }
        Account account = new Account();

        //find customer by id then create Account
        Optional<Customer> optionalCustomer = customerRepo.findById(accountRequestDTO.customerId());

        if (optionalCustomer.isPresent()) {
            account.setCustomer(optionalCustomer.get()); //unwrap optional
            account.setName(accountRequestDTO.accountName());
            account.setOpeningBalance(accountRequestDTO.openingBalance());
            account.setOpeningBalance(accountRequestDTO.openingBalance());
            account.setBalance(accountRequestDTO.openingBalance());
            account.setSortCode(sortcode);

            //save account to database
            accountRepository.save(account);
            return accountMapper(account);
        } else {
            throw new AccountNotFoundException("Customer not found with id");
        }

    }

    @Override
    public BigDecimal closeAccount(long number) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(number);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get(); //unwrap
            BigDecimal balance = account.getBalance();
            account.setBalance(BigDecimal.ZERO);
            account.setClosed(true);
            accountRepository.save(account);
            return balance;
        } else {
            throw new AccountNotFoundException(number);
        }
    }

    @Override
    public AccountDTO findAccountByNumber(long number) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(number);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            return accountMapper(account);
        } else {
            throw new AccountNotFoundException(number);
        }

    }

}
