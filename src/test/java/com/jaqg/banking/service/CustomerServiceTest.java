package com.jaqg.banking.service;


import com.jaqg.banking.dto.CustomerDTO;
import com.jaqg.banking.entity.Customer;
import com.jaqg.banking.repository.CustomerRepository;
import com.jaqg.banking.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepo;

    private Customer testCustomer;

    private List<Customer> customerList;


    @BeforeEach
    public void setUp() {
        testCustomer = new Customer("Dan Jones");

    }

    @Test
    public void addNewCustomerTest() {
        CustomerService customerService = new CustomerServiceImpl(customerRepo);
        given(customerRepo.save(testCustomer)).willReturn((testCustomer));
        CustomerDTO testCustomer = customerService.createCustomer("Dan Jones");
        Customer customer = new Customer(testCustomer.fullName());
        assertThat(customer.getFullName()).isEqualTo("Dan Jones");
    }

    @Test
    public void deleteCustomerTest() {
        CustomerService customerService = new CustomerServiceImpl(customerRepo);
        given(customerRepo.findById(2L)).willReturn(Optional.of(testCustomer));
        var testCustomer = customerService.deleteCustomer(2L);
        assertThat(testCustomer).isEqualTo(testCustomer);
    }

    @Test
    public void retrieveAllCustomersCustomersTest() {
        CustomerService customerService = new CustomerServiceImpl(customerRepo);
        when(customerList.containsAll(customerList)).thenReturn(true);
        assertTrue(customerList.containsAll(customerList));
    }


}
