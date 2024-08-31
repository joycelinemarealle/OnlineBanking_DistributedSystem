package com.jaqg.banking.services;

import com.jaqg.banking.dto.CustomerDTO;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.mapper.CustomerMapper;
import com.jaqg.banking.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    // Implementing Get Request DTO

    @Override
    public CustomerDTO customerGetRequest(Long ID) {
        Optional<Customer> customer = customerRepo.findById(ID);
        return CustomerMapper.toDTO(customer.orElse(null));
    }
    // Implementing Post Request DTO

    @Override
    public CustomerDTO customerPostRequest(String fullName) {
        Customer customer = new Customer(fullName);
//        Customer customer = CustomerMapper.toCustomer(customer);
        Customer savedCustomer = customerRepo.save(customer);
        return CustomerMapper.toDTO(savedCustomer);
    }

    // Implementing Delete Request DTO
    @Override
    public BigDecimal customerDeleteRequest(Long id) {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
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
    public Optional<Customer> getCustomer(Long ID) {

        return customerRepo.findById(ID);
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepo.findAll().stream().map(CustomerMapper::toDTO).toList();
    }
}




