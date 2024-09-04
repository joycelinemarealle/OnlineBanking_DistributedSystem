package com.jaqg.banking.services;

import com.jaqg.banking.dto.CustomerDTO;
import com.jaqg.banking.entities.Customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerDTO customerGetRequest(Long ID);

    CustomerDTO customerPostRequest(String fullName);

    BigDecimal customerDeleteRequest(Long id);

    Optional<Customer> getCustomer(Long ID);

    List<CustomerDTO> findAll();

}




