package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.CustomerDTO;
import com.jaqg.banking.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> retrieveAllCustomers() {
        return this.customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerByID(@PathVariable Long id) {
        return customerService.customerGetRequest(id);
    }

    @PostMapping
    public CustomerDTO addNewCustomer(@RequestBody String fullName) {
        return customerService.customerPostRequest(fullName);
    }

    @DeleteMapping("/{id}")
    public BigDecimal deleteCustomer(@PathVariable("id") Long ID) {
        return customerService.customerDeleteRequest(ID);
    }

}
