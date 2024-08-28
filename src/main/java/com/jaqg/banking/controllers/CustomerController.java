package com.jaqg.banking.controllers;
import com.jaqg.banking.dto.CustomerDeleteRequest;
import com.jaqg.banking.dto.CustomerGetRequest;
import com.jaqg.banking.dto.CustomerGetRequestMapper;
import com.jaqg.banking.dto.CustomerPostRequest;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.repos.CustomerRepo;
import com.jaqg.banking.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RestController
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public List<Customer> retrieveAllCustomers(){


        return this.customerService.findAll();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerGetRequest> getCustomerByID (@PathVariable Long ID){
        CustomerGetRequest customerGetRequest = customerService.customerGetRequest(ID);
        return new ResponseEntity<>(customerGetRequest, HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerPostRequest> addNewCustomer (@RequestBody String fullName){
        CustomerPostRequest customerPostRequest1 = customerService.customerPostRequest(fullName);
        if (customerPostRequest1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerPostRequest1, HttpStatus.CREATED);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<CustomerDeleteRequest> deleteCustomer (@PathVariable("id") Long ID) {
        CustomerDeleteRequest customerDeleteRequest = customerService.customerDeleteRequest(ID);
        if (customerDeleteRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerDeleteRequest, HttpStatus.CREATED);

    }

}

