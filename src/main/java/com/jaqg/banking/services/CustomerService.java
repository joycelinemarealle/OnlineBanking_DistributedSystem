package com.jaqg.banking.services;
import com.jaqg.banking.dto.*;
import com.jaqg.banking.entities.Customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerGetRequest customerGetRequest(Long ID);

    CustomerPostRequest customerPostRequest(String fullName);

    BigDecimal customerDeleteRequest(Long id);

    Optional<Customer> getCustomer(Long ID);

    List<CustomerGetRequest> findAll();

    Customer addNewCustomer(Customer customer);
}




