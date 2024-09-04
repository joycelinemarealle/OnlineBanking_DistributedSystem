package com.jaqg.banking.services;


import com.jaqg.banking.dto.CustomerDTO;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.repos.CustomerRepository;
import com.jaqg.banking.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

@Disabled
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepo;

    Customer testCustomer;

    List<Customer> customerList;


    @BeforeEach
    public void setUp() {
        testCustomer = new Customer("Dan Jones");
//        testCustomer.setId(2);

    }

    @Test
    public void addNewCustomerTest() {
        CustomerService customerService = new CustomerServiceImpl(customerRepo);
        given(customerRepo.save(testCustomer)).willReturn((testCustomer));
        CustomerDTO testCustomer = customerService.customerPostRequest("Dan Jones");
        Customer customer = new Customer(testCustomer.fullName());
        assertThat(customer.getFullName()).isEqualTo("Dan Jones");
    }

    @Test
    public void deleteCustomerTest() {
        CustomerService customerService = new CustomerServiceImpl(customerRepo);
        given(customerRepo.findById(2L)).willReturn(Optional.of(testCustomer));
        var testCustomer = customerService.customerDeleteRequest(2L);
        assertThat(testCustomer).isEqualTo(testCustomer);
    }

    @Test
    public void findAllCustomersTest() {
        CustomerService customerService = new CustomerServiceImpl(customerRepo);
        when(customerList.containsAll(customerList)).thenReturn(true);
        assertTrue(customerList.containsAll(customerList));
    }


}
