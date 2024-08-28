package com.jaqg.banking.services;
import com.jaqg.banking.dto.*;
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

    // Implementing Get Request DTO

    public CustomerGetRequest customerGetRequest(Long ID) {
        Optional<Customer> customer =  customerRepo.findById(ID);
        return CustomerGetRequestMapper.toDTO(customer.orElse(null));
    }
    // Implementing Post Request DTO

    public CustomerPostRequest customerPostRequest(String fullName) {
        Customer customer = new Customer(fullName);
//        Customer customer = CustomerPostRequestMapper.toCustomer(customer);
        Customer savedCustomer = customerRepo.save(customer);
        return CustomerPostRequestMapper.toDTO(savedCustomer);
    }

    // Implementing Delete Request DTO
    public CustomerDeleteRequest customerDeleteRequest(Long id) {

        Optional<Customer> optionalCustomer =  customerRepo.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setRemoved(true);
            customer = customerRepo.save(customer);
            return CustomerDeleteRequestMapper.toDelete(customer) ;
        } else {
            return null;
        }
    }

    public Optional<Customer> getCustomer(Long ID){

        return customerRepo.findById(ID);
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Customer addNewCustomer(Customer customer){
        return customerRepo.save(customer);
    }




    }




