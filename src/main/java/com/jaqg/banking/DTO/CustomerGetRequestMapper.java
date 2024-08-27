package com.jaqg.banking.DTO;

import com.jaqg.banking.entities.Customer;

public class CustomerGetRequestMapper {
    public static CustomerGetRequest toDTO(Customer customer) {
        return new CustomerGetRequest(customer.getUniqueID(),
                customer.getFullName(), customer.getAccounts());
    }
}
