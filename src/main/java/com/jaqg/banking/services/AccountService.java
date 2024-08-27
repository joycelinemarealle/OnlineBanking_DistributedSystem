package com.jaqg.banking.services;
import com.jaqg.banking.DTO.CreateAccountResponseDTO;
import com.jaqg.banking.DTO.DeleteAccountRequestDTO;
import com.jaqg.banking.DTO.GetAccountResponseDTO;
import com.jaqg.banking.entities.Customer;
import com.jaqg.banking.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountService {
   List < GetAccountResponseDTO>  retrieveAllAccounts();
   CreateAccountResponseDTO createAccount(Customer customer, String name, BigDecimal openingBalance);
   DeleteAccountRequestDTO closeAccount(long number) throws AccountNotFoundException;
   GetAccountResponseDTO findAccountByNumber(long number) throws AccountNotFoundException;

}
