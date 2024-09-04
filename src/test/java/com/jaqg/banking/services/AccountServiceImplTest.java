package com.jaqg.banking.services;

import com.jaqg.banking.dto.AccountDTO;
import com.jaqg.banking.dto.AccountRequestDTO;
import com.jaqg.banking.entities.Account;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.repos.AccountRepository;
import com.jaqg.banking.repos.CustomerRepository;
import com.jaqg.banking.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepo;
    @Autowired
    private AccountService accountService;

    private Account account1;
    private Account account2;
    private List<Account> accounts;
    private Customer customer;
    private AccountDTO accountResponse1;
    private AccountDTO accountResponse2;
    private AccountRequestDTO accountRequest1;

    @BeforeEach
    void setUp() {
        //Create accounts
//        accountRepository = mock(AccountRepository.class);
//        customerRepo = mock (CustomerRepo.class);
        //create customer
        customer = new Customer("Joyceline Marealle");
//        customer.setId(1L);


        //Create accounts
        accountService = new AccountServiceImpl(accountRepository, customerRepo,1111);
        accounts = new ArrayList<>();
        account1 = new Account("Savings", new BigDecimal("100"), customer, 1111);
        account2 = new Account("Checkings", new BigDecimal("200"), customer, 2222);

        //Create accountDTOs that match the account above
        accountResponse1 = new AccountDTO(1234L, 1111,
                "Savings", new BigDecimal(100),
                new ArrayList<>(), new BigDecimal(100),
                1L);
        accountResponse2 = new AccountDTO(5678L, 2222,
                "Checkings", new BigDecimal(200),
                new ArrayList<>(), new BigDecimal(200),
                1L);

        //Create accountRequest DTO
        accountRequest1 = new AccountRequestDTO(1L, "Savings", new BigDecimal("100"));

        //add to list
        accounts.add(account1);
        accounts.add(account2);
    }


    @Test
    void retrieveAllAccounts() {

        //Test the size and elements of List
        when(accountRepository.findByIsClosedFalse()).thenReturn(accounts);

        //Execute service method
        List<AccountDTO> accountDTOList = accountService.retrieveAllAccounts();

        //Assertions
        assertThat(accountDTOList).hasSize(2);
//        assertThat(accountDTOList).containsExactly(accountResponse1, accountResponse2);

        //Test for each attribute of account
//        assertEquals(accountResponse1.number(), accountDTOList.get(0).number());
        assertEquals(accountResponse1.name(), accountDTOList.get(0).name());
        assertEquals(accountResponse1.openingBalance(), accountDTOList.get(0).openingBalance());
        assertEquals(accountResponse1.balance(), accountDTOList.get(0).balance());
        assertEquals(accountResponse1.sortCode(), accountDTOList.get(0).sortCode());

        //Verify mock if method called
        verify(accountRepository).findByIsClosedFalse();
    }

    @Test
    void findAccountByNumber() {

        Long accountNumber = account1.getNumber();
        Optional<Account> optionalAccount = Optional.ofNullable(account1);

        //Mock findById()
        when(accountRepository.findByIdNumberAndIsClosedFalse(accountNumber)).thenReturn(optionalAccount);

        //Execute service method
        AccountDTO account = accountService.findAccountByNumber(accountNumber);

        //Asserts results
        assertThat(account1.getNumber()).isEqualTo(account.number());
        assertThat(account1.getOpeningBalance()).isEqualTo(account.openingBalance());
        assertThat(account1.getBalance()).isEqualTo(account.balance());
        assertThat(account1.getSortCode()).isEqualTo(account.sortCode());

        //Verify Mock
        verify(accountRepository).findByIdNumberAndIsClosedFalse(accountNumber);

    }

    @Test
    void closeAccount() {
        Long accountNumber = account1.getNumber();
        Optional<Account> optionalAccount = Optional.ofNullable(account1);

        //Mock findById()
        when((accountRepository).findByIdNumberAndIsClosedFalse(accountNumber)).thenReturn(optionalAccount);

        //Capture original balance
        BigDecimal originalBalance = account1.getBalance();

        //Call service method
        BigDecimal closingBalance = accountService.closeAccount(accountNumber);

        //Assert results
        //closing balance equals to original account1 balance
        assertThat(originalBalance).isEqualTo(closingBalance);

        //closed account balance is zero
        assertThat(account1.getBalance()).isEqualTo(BigDecimal.ZERO);

        //Verify mock used
        verify(accountRepository).findByIdNumberAndIsClosedFalse(accountNumber);
        verify(accountRepository).save(account1);


    }

    @Test
    void createAccount() {
        Long customerId = accountRequest1.customerId();
        Optional<Customer> optionalCustomer = Optional.ofNullable(customer);

        //mock customerRepo.findBy
        when(customerRepo.findByIdAndIsRemovedFalse(customerId)).thenReturn(optionalCustomer);
        when(accountRepository.save(account1)).thenReturn(account1);

        //assert
        AccountDTO accountResponse = accountService.createAccount(accountRequest1);

        assertThat(accountResponse.name()).isEqualTo(accountRequest1.accountName());
        assertThat(accountResponse.openingBalance()).isEqualTo(accountRequest1.openingBalance());

        //Verify
        verify(customerRepo).findByIdAndIsRemovedFalse(customerId);

    }
}
