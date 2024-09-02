package com.jaqg.banking.services;


import com.jaqg.banking.dto.CustomerDTO;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.repos.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@Disabled
public class CustomerServiceTest {
    @Mock
    CustomerRepo customerRepo;

    Customer testCustomer;

    List<Customer> customerList;


    @BeforeEach
    public void setUp() {
        testCustomer = new Customer("Dan Jones");
//        testCustomer.setId(2);

    }

    @Test
    public void getCustomerByIdTest() {
        CustomerService customerService = new CustomerServiceImpl(customerRepo);
        given(customerRepo.findById(2L)).willReturn(Optional.of(testCustomer));
        Optional<Customer> testCustomer = customerService.getCustomer(2L);
        assertThat(testCustomer).isPresent();
        Customer customer = testCustomer.get();
        assertThat(customer.getId()).isEqualTo(2L);
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
        assertThat(testCustomer).isNotIn();
    }

    @Test
    public void findAllCustomersTest() {
        CustomerService customerService = new CustomerServiceImpl(customerRepo);
        when(customerList.containsAll(customerList)).thenReturn(true);
        assertTrue(customerList.containsAll(customerList));
    }


}
