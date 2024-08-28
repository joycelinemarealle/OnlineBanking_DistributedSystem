package com.jaqg.banking.dto;

import com.jaqg.banking.entities.Customer;

public class CustomerGetRequestMapper {
    public static CustomerGetRequest toDTO(Customer customer) {
        return new CustomerGetRequest(customer.getId(),
                customer.getFullName(), customer.getAccounts());
    }
}
