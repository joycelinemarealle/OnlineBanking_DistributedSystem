package com.jaqg.banking.services;
import com.jaqg.banking.dto.AccountResponseDTO;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountService {
   List < AccountResponseDTO>  retrieveAllAccounts();
   AccountResponseDTO createAccount(Customer customerId, String accountName, BigDecimal openingBalance);
   BigDecimal closeAccount(long number) throws AccountNotFoundException;
   AccountResponseDTO findAccountByNumber(long number) throws AccountNotFoundException;
}
