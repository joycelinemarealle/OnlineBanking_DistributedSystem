package com.jaqg.banking.services;

import com.jaqg.banking.entities.Account;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import  org.springframework.context.ApplicationContext;

import java.util.List;

public class CustomerService {
    public List<Account> retrieveAllAccounts;
    @SpringBootApplication

    ApplicationContext context = SpringBootApplication.run(CustomerService.class, args);
    CustomerService customerService = context.getBean(CustomerService.class);

}
