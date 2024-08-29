package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.CustomerGetRequest;
import com.jaqg.banking.dto.CustomerPostRequest;
import com.jaqg.banking.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<CustomerGetRequest> retrieveAllCustomers(){
        return this.customerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerGetRequest> getCustomerByID (@PathVariable Long id){
        CustomerGetRequest customerGetRequest = customerService.customerGetRequest(id);
        return new ResponseEntity<>(customerGetRequest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerPostRequest> addNewCustomer (@RequestBody String fullName){
         CustomerPostRequest customerPostRequest1 = customerService.customerPostRequest(fullName);
        if (customerPostRequest1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerPostRequest1, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BigDecimal> deleteCustomer (@PathVariable("id") Long ID) {
        BigDecimal value = customerService.customerDeleteRequest(ID);
        if (value == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(value, HttpStatus.OK);

    }

}

