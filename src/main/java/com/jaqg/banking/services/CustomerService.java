package com.jaqg.banking.services;
import com.jaqg.banking.entities.Customer
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import  org.springframework.context.ApplicationContext;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    //Repo injection
    @Autowired
    private CustomerRepo customerRepo;

    public Customer getCustomer(Long ID){
        return customerRepo.findById(ID)
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Customer addNewCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    public Customer updateCustomer(Long ID) {
        Optional<Customer> optionalCustomer = customerRepo.findById(ID);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setRemoved(true);
            customerRepo.save(customer)
        } else {
            return null;
        }


    }



}
