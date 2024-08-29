package com.jaqg.banking.services;

import com.jaqg.banking.dto.AccountResponseDTO;
import com.jaqg.banking.dto.CreateAccountRequestDTO;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import com.jaqg.banking.exceptions.AccountNotFoundException;

import static com.jaqg.banking.mapper.AccountMapper.accountMapper;

@Service
public class LocalAccountService implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepo customerRepo;
    private final Logger logger = LoggerFactory.getLogger(LocalAccountService.class);

    //inject repo
    public LocalAccountService(AccountRepository accountRepository, CustomerRepo customerRepo) {
        this.accountRepository = accountRepository;
        this.customerRepo = customerRepo;

    }

    @Override
    public List<AccountResponseDTO> retrieveAllAccounts() {
        logger.info("Getting accounts from Account Repository");

        //Retrieve all accounts from the repository
        List<Account> accounts = accountRepository.findAll();

        //Convert each Account Entity to a AccountResponseDTO
        return accounts.stream()
                .map(account -> accountMapper(account))
                .toList(); //collect to a list
    }

    @Override
    public AccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO) {
        Account account = new Account();

        //find customer by id then create Account
        Optional<Customer> optionalCustomer = customerRepo.findById(createAccountRequestDTO.customerId());
        account.setCustomer(optionalCustomer.get()); //unwrap optional
        account.setName(createAccountRequestDTO.accountName());
        account.setOpeningBalance(createAccountRequestDTO.openingBalance());
        account.setBalance(createAccountRequestDTO.openingBalance());

        //save account to database
        accountRepository.save(account);
        return accountMapper(account);
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
            throw new AccountNotFoundException("Account not found with number" + number);
        }
    }

    @Override
    public AccountResponseDTO findAccountByNumber(long number) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(number);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            return accountMapper(account);
        } else {
            throw new AccountNotFoundException("Account not found with number" + number);
        }

    }

    public void deleteAccount(long number) {
        accountRepository.deleteById(number);
    }

    private int generatesortcode() {
        //Logic to generate sortCode
        return 1234; //placeholder for now
    }


}
