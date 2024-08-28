package com.jaqg.banking.dto;

import com.jaqg.banking.entities.Customer;

public class CustomerPostRequestMapper {
    public static CustomerPostRequest toDTO(Customer customer) {
        return new CustomerPostRequest(customer.getId(),
                customer.getFullName(), customer.getAccounts());
    }

//    public static Customer toCustomer(CustomerPostRequest customerPostRequest) {
//        return new Customer(customerPostRequest.ID(),
//                customerPostRequest.name(), customerPostRequest.accounts());
//    }
}
