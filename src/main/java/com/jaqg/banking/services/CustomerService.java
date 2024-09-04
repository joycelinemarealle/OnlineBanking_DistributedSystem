package com.jaqg.banking.services;

import com.jaqg.banking.dto.CustomerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {

    CustomerDTO customerGetRequest(Long id);

    CustomerDTO customerPostRequest(String fullName);

    BigDecimal customerDeleteRequest(Long id);

    List<CustomerDTO> findAll();

}




