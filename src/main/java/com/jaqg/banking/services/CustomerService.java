package com.jaqg.banking.services;

import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    //Repo injection
    @Autowired
    private CustomerRepo customerRepo;

    public Optional<Customer> getCustomer(Long ID){
        return customerRepo.findById(ID);
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
            return customerRepo.save(customer);
        } else {
            return null;
        }


    }



}
