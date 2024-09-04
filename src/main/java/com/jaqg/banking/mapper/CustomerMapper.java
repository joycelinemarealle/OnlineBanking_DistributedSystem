package com.jaqg.banking.mapper;

import com.jaqg.banking.dto.CustomerDTO;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;

public class CustomerMapper {
    public static CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFullName(),
                customer.getAccounts()
                        .stream().filter(e -> !e.isClosed())
                        .map(Account::getNumber)
                        .toList()
        );
    }

    public static Customer toCustomer(CustomerDTO dto) {
        return new Customer(dto.fullName());
    }
}
