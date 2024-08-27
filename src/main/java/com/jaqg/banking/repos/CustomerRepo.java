package com.jaqg.banking.repos;

import com.jaqg.banking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {


}
