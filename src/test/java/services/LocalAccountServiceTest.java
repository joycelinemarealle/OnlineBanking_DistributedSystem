package services;

import com.jaqg.banking.dto.AccountResponseDTO;
import com.jaqg.banking.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocalAccountServiceTest {
    @Mock
    private AccountService accountService;

    private AccountResponseDTO accountResponse1;
    private AccountResponseDTO accountResponse2;
    private List<AccountResponseDTO> accountResponses;

    @BeforeEach
    void setUp(){
        accountService = mock(AccountService.class);
        accountResponses = new ArrayList<>();
        accountResponse1 = new AccountResponseDTO(1234L, 1111,
                "Savings", new BigDecimal(100),
                new ArrayList<>(), new BigDecimal(100),
                1L);
        accountResponse2 = new AccountResponseDTO(5678L, 2222,
                "Checkings", new BigDecimal(200),
                new ArrayList<>(), new BigDecimal(200),
                2L);
        //add to list
        accountResponses.add(accountResponse1);
        accountResponses.add(accountResponse2);
    }


    @Test
    void retrieveAllAccounts(){
        //check that size of array increase to two
      when(accountService.retrieveAllAccounts().size()).thenReturn(2);
        assertEquals(2, accountResponses.size() );
    }
}
