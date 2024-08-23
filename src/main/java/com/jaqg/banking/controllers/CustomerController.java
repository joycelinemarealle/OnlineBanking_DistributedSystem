package com.jaqg.banking.controllers;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    private List<Account> getAccounts() {
        return customerService.retrieveAllAccounts;
    }

    }

