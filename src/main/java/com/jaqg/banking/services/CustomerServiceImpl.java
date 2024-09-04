package com.jaqg.banking.services;

import com.jaqg.banking.dto.CustomerGetRequest;
import com.jaqg.banking.dto.CustomerPostRequest;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.dto.CustomerDTO;
import com.jaqg.banking.entities.Customer;
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
    public CustomerDTO customerGetRequest(Long ID) {
        Optional<Customer> customer = customerRepo.findById(ID);
        return CustomerMapper.toDTO(customer.orElse(null));
    }
    // Implementing Post Request DTO

    @Override
    public CustomerDTO customerPostRequest(String fullName) {
        Customer customer = new Customer(fullName);
//        Customer customer = CustomerMapper.toCustomer(customer);
        Customer savedCustomer = customerRepo.save(customer);
        return CustomerMapper.toDTO(savedCustomer);
    }

    // Implementing Delete Request DTO
    @Override
    public BigDecimal customerDeleteRequest(Long id) {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
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

        } else {
            return null;
        }
    }

    @Override
    public Optional<Customer> getCustomer(Long ID) {

        return customerRepo.findById(ID);
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepo.findAll().stream().map(CustomerMapper::toDTO).toList();
    }
}




