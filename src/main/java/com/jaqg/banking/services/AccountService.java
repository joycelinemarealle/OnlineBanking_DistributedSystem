package com.jaqg.banking.services;
import com.jaqg.banking.dto.AccountResponseDTO;
import com.jaqg.banking.dto.CreateAccountRequestDTO;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountService {
   List < AccountResponseDTO>  retrieveAllAccounts();
   AccountResponseDTO createAccount(CreateAccountRequestDTO createAccountDTO);
   BigDecimal closeAccount(long number) throws AccountNotFoundException;
   AccountResponseDTO findAccountByNumber(long number) throws AccountNotFoundException;
}
