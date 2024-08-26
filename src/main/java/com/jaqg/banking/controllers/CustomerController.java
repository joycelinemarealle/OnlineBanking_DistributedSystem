package com.jaqg.banking.controllers;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.repos.CustomerRepo;
import com.jaqg.banking.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RestController
public class CustomerController {
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public List<Customer> retrieveAllCustomers(){
        return this.customerService.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer findCustomerById(@PathVariable Long ID){
        return this.customerService.getCustomer(ID).orElse(null);
    }

    @PostMapping("/customer")
    public Customer addNewCustomer(@RequestBody Customer customer){
        Customer newCustomer = this.customerService.addNewCustomer(customer);
        return newCustomer;
    }

    @DeleteMapping("/customer/{id}")
    public Customer removeCustomer(
            @PathVariable("id") Long ID) {
        Customer customer = this.customerService.updateCustomer(ID);
        return customer;
    }

}

