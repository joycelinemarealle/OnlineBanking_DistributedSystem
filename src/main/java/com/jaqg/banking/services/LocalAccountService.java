package com.jaqg.banking.services;

import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.repos.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class LocalAccountService implements AccountService{

    private final AccountRepository accountRepository;
    private final Logger logger = LoggerFactory.getLogger(LocalAccountService.class);

    //inject repo
    @Autowired
    public LocalAccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;

    }

    public Account createAccount(Customer customer, String name, BigDecimal openingBalance){
        Account account = new Account();
        account.setCustomer(customer);
        account.setName(name);
        account.setOpeningBalance(openingBalance);
        account.setBalance(openingBalance);

        //save account to database
        return accountRepository.save(account);
    }

    public BigDecimal closeAccount(long number) throws AccountNotFoundException{
        try{ Account account = accountRepository.findAccountByNumber(number);
            BigDecimal balance = account.getBalance();

            //Return balance to the customer in cash
            account.setBalance(BigDecimal.ZERO);

            //mark account as closed
            account.setClosed(true);
            accountRepository.save(account);

            //accountRepository.delete(account);

            return balance;

        } catch(Exception e ){
            e = new AccountNotFoundException("Account not found");

        }
        return null;
    }
    public Account findAccountByNumber(long number){
       return accountRepository.findAccountByNumber(number);
    }

    public void deleteAccount(long number){
        accountRepository.deleteById(number);
    }

    private int generatesortcode(){
        //Logic to generate sortCode
        return 1234; //placeholder for now
    }
    @Override
    public List<Account> retrieveAllAccounts() {
        logger.info("Getting accounts from Account Repository");
        return accountRepository.getAllAccounts();
    }
}
