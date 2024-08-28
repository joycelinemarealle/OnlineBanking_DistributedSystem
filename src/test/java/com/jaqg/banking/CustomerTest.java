package com.jaqg.banking;


import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.repos.CustomerRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.jaqg.banking.repos.CustomerRepo.*;

@DataJpaTest
public class CustomerTest {
    @Autowired
    private CustomerRepo customerRepo;

    private Customer testCustomer;

    @BeforeEach
    public void setUp() {
        testCustomer = new Customer();
        testCustomer.getFullName();
        testCustomer.getUniqueID();
        customerRepo.save(testCustomer);
    }

//    @AfterEach
//    public void  tearDown() {
//        customerRepo.isRemoved(testCustomer);
//    }

    @Test
    public void savedCustomer() {
        Customer savedCustomer = CustomerRepo.findById(testCustomer.getUniqueID());

    }

}
