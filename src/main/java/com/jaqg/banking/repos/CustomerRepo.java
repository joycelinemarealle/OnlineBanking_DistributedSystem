package com.jaqg.banking.repos;

import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Long> {



}
