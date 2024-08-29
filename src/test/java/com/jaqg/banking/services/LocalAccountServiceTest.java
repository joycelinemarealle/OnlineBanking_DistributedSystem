package com.jaqg.banking.services;

import com.jaqg.banking.dto.AccountResponseDTO;
import com.jaqg.banking.dto.CreateAccountRequestDTO;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
public class LocalAccountServiceTest {
    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private CustomerRepo customerRepo;
    @Autowired
    private AccountService accountService;

    private Account account1;
    private Account account2;
    private List<Account> accounts;
    private Customer customer;
    private AccountResponseDTO accountResponse1;
    private AccountResponseDTO accountResponse2;
    private CreateAccountRequestDTO accountRequest1;
    private List<AccountResponseDTO> accountResponses;

    @BeforeEach
    void setUp(){
        //Create accounts
//        accountRepository = mock(AccountRepository.class);
//        customerRepo = mock (CustomerRepo.class);
        //create customer
        customer = new Customer("Joyceline Marealle");

        //Create accounts
        accounts = new ArrayList<>();
        account1 = new Account(1234L,"Savings", new BigDecimal(100),
                new BigDecimal(100), customer, 1111);
        account2 = new Account(5678L,"Checkings", new BigDecimal(200),
                new BigDecimal(200), customer, 2222);

        //Create accountDTOs that match the account above
        accountResponse1 = new AccountResponseDTO(1234L, 1111,
                "Savings", new BigDecimal(100),
                new ArrayList<>(), new BigDecimal(100),
                1L);
        accountResponse2 = new AccountResponseDTO(5678L, 2222,
                "Checkings", new BigDecimal(200),
                new ArrayList<>(), new BigDecimal(200),
                2L);

        //Create accountRequest DTO
        accountRequest1 = new CreateAccountRequestDTO(1L,"Savings",new BigDecimal(200));

        //add to list
        accounts.add(account1);
        accounts.add(account2);
    }


    @Test
    void retrieveAllAccounts(){
        //Test the size and elements of List
        when(accountRepository.findAll()).thenReturn(accounts);
        List<AccountResponseDTO> accountResponseDTOList = accountService.retrieveAllAccounts();
        assertThat(accountResponseDTOList).hasSize(2);
        assertThat(accountResponseDTOList).contains(accountResponse1);
        assertThat(accountResponseDTOList).contains(accountResponse2);

        //Test for each attribute of account
        assertEquals(accountResponse1.number(), accountResponseDTOList.get(0).number());
        assertEquals(accountResponse1.name(), accountResponseDTOList.get(0).name());
        assertEquals(accountResponse1.openingBalance(), accountResponseDTOList.get(0).openingBalance());
        assertEquals(accountResponse1.balance(), accountResponseDTOList.get(0).balance());
        assertEquals(accountResponse1.sortCode(), accountResponseDTOList.get(0).sortCode());

        //Verify mock if method called
        verify(accountRepository.findAll());
    }

    @Test
    void findAccountByNumber(){
        Long accountNumber = account1.getNumber();
        Optional<Account> optionalAccount = Optional.ofNullable(account1);

        //Mock findById()
        when(accountRepository.findById(accountNumber)).thenReturn(optionalAccount);

        //Asserts results
        AccountResponseDTO accountByNumber = accountService.findAccountByNumber(accountNumber);
        assertThat(optionalAccount.get()).isEqualTo(account1);
        assertThat(optionalAccount.get().getNumber()).isEqualTo(accountByNumber);
        assertThat(optionalAccount.get().getName()).isEqualTo(account1.getName());
        assertThat(optionalAccount.get().getOpeningBalance()).isEqualTo(account1.getOpeningBalance());
        assertThat(optionalAccount.get().getBalance()).isEqualTo(account1.getBalance());
        assertThat(optionalAccount.get().getSortCode()).isEqualTo(account1.getSortCode());

        //Verify Mock
        verify(accountRepository.findById(accountNumber));

    }

    @Test
    void closeAccount(){
        Long accountNumber = account1.getNumber();
        Optional<Account> optionalAccount = Optional.ofNullable(account1);

        //Mock findById()
        when (accountRepository.findById(accountNumber)).thenReturn(optionalAccount);

        //Assert results
        BigDecimal closingBalance = accountService.closeAccount(accountNumber);

        //closing balance equals to original account1 balance
        assertThat(account1.getBalance()).isEqualTo(closingBalance);



    }
    @Test
    void createAccount(){
        Long customerId = accountRequest1.customerId();
        Optional<Customer> optionalCustomer= Optional.ofNullable(customer);

        //mock customerRepo.findBy
        when(customerRepo.findById(customerId)).thenReturn(optionalCustomer);

        //assert
        AccountResponseDTO accountResponse = accountService.createAccount(accountRequest1);

        assertThat(accountResponse.name()).isEqualTo(accountRequest1.accountName());
        assertThat(accountResponse.openingBalance()).isEqualTo(accountRequest1.openingBalance());

    }
}
