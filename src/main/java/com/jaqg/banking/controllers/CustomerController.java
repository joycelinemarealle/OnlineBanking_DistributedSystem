package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.CustomerDeleteRequest;
import com.jaqg.banking.dto.CustomerGetRequest;
import com.jaqg.banking.dto.CustomerPostRequest;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<CustomerGetRequest> getCustomerByID (@PathVariable Long id){
        CustomerGetRequest customerGetRequest = customerService.customerGetRequest(id);
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

