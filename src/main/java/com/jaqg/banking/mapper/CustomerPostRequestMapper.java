package com.jaqg.banking.mapper;

import com.jaqg.banking.dto.CustomerPostRequest;
import com.jaqg.banking.entities.Customer;

public class CustomerPostRequestMapper {
    public static CustomerPostRequest toDTO(Customer customer) {
        return new CustomerPostRequest(customer.getId(),
                customer.getFullName(), customer.getAccounts().stream().map(e -> e.getNumber()).toList());
    }

//    public static Customer toCustomer(CustomerPostRequest customerPostRequest) {
//        return new Customer(customerPostRequest.id(),
//                customerPostRequest.fullName(), customerPostRequest.accounts());
//    }
}
