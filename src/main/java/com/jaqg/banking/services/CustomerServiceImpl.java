package com.jaqg.banking.services;
import com.jaqg.banking.dto.*;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.mapper.CustomerGetRequestMapper;
import com.jaqg.banking.mapper.CustomerPostRequestMapper;
import com.jaqg.banking.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    //Repo injection
    @Autowired
    private CustomerRepo customerRepo;

    // Implementing Get Request DTO

    @Override
    public CustomerGetRequest customerGetRequest(Long ID) {
        Optional<Customer> customer =  customerRepo.findById(ID);
        return CustomerGetRequestMapper.toDTO(customer.orElse(null));
    }
    // Implementing Post Request DTO

    @Override
    public CustomerPostRequest customerPostRequest(String fullName) {
        Customer customer = new Customer(fullName);
//        Customer customer = CustomerPostRequestMapper.toCustomer(customer);
        Customer savedCustomer = customerRepo.save(customer);
        return CustomerPostRequestMapper.toDTO(savedCustomer);
    }

    // Implementing Delete Request DTO
    @Override
    public BigDecimal customerDeleteRequest(Long id) {
        Optional<Customer> optionalCustomer =  customerRepo.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setRemoved(true);
            customer = customerRepo.save(customer);
            return new BigDecimal("50.55");
        } else {
            return null;
        }
    }

    @Override
    public Optional<Customer> getCustomer(Long ID){

        return customerRepo.findById(ID);
    }

    @Override
    public List<CustomerGetRequest> findAll() {
        return customerRepo.findAll().stream().map(e->CustomerGetRequestMapper.toDTO(e)).toList();
    }

    @Override
    public Customer addNewCustomer(Customer customer){
        return customerRepo.save(customer);
    }
}




