package com.jaqg.banking.mapper;

import com.jaqg.banking.dto.CustomerGetRequest;
import com.jaqg.banking.entities.Customer;

public class CustomerGetRequestMapper {
    public static CustomerGetRequest toDTO(Customer customer) {
        return new CustomerGetRequest(customer.getId(),
                customer.getFullName(), customer.getAccounts().stream().filter(e->!e.isClosed()).map(e -> e.getNumber()).toList());
    }
}
