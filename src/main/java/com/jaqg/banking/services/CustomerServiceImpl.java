package com.jaqg.banking.services;

import com.jaqg.banking.dto.CustomerGetRequest;
import com.jaqg.banking.dto.CustomerPostRequest;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.dto.CustomerDTO;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.exceptions.CustomerNotFoundException;
import com.jaqg.banking.mapper.CustomerGetRequestMapper;
import com.jaqg.banking.mapper.CustomerPostRequestMapper;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.CustomerRepo;
import com.jaqg.banking.mapper.CustomerMapper;
import com.jaqg.banking.repos.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepo;
    private final LocalAccountService localAccountService;
    private final AccountRepository accountRepository;

    public CustomerServiceImpl(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    // Implementing Get Request DTO

    @Override
    public CustomerDTO customerGetRequest(Long id) {
        Customer customer = customerRepo.findByIdAndIsRemovedFalse(id).orElseThrow(() -> new CustomerNotFoundException(id));
        return CustomerMapper.toDTO(customer);
    }
    // Implementing Post Request DTO

    @Override
    public CustomerDTO customerPostRequest(String fullName) {
        Customer customer = customerRepo.save(new Customer(fullName));
        return CustomerMapper.toDTO(customer);
    }

    // Implementing Delete Request DTO
    @Override
    public BigDecimal customerDeleteRequest(Long id) {
        Customer customer = customerRepo.findByIdAndIsRemovedFalse(id).orElseThrow(() -> new CustomerNotFoundException(id));
        customer.setRemoved(true);
        customer = customerRepo.save(customer);
        for (Account account : customer.getAccounts()) {
            BigDecimal balance = account.getBalance();
            account.setBalance(BigDecimal.ZERO);
            account.setClosed(true);
            accountRepository.save(account);
            return balance;
        }
            //return funds from all accounts, and summarize all balances
//            return new BigDecimal("50.55");

    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepo.findByIsRemovedFalse().stream().map(CustomerMapper::toDTO).toList();
    }
}




