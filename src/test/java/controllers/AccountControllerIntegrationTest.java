package controllers;

import com.jaqg.banking.controllers.AccountController;
import com.jaqg.banking.dto.AccountResponseDTO;
import com.jaqg.banking.dto.CreateAccountRequestDTO;
import com.jaqg.banking.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.print.attribute.standard.Media;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@Import(AccountController.class)
@WebMvcTest(AccountController.class)
public class AccountControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;
    private List<AccountResponseDTO> accountResponses;
    private AccountResponseDTO accountResponse1;
    private AccountResponseDTO accountResponse2;
    private CreateAccountRequestDTO accountRequest;

    @BeforeEach
    void setup() {

        accountResponse1 = new AccountResponseDTO(1234L, 1111,
                "Savings", new BigDecimal(100),
                new ArrayList<>(), new BigDecimal(100),
                1L);
        accountResponse2 = new AccountResponseDTO(5678L, 2222,
                "Checkings", new BigDecimal(200),
                new ArrayList<>(), new BigDecimal(200),
                2L);

        accountResponses = new ArrayList<>();
        accountResponses.add(accountResponse1);
        accountResponses.add(accountResponse2);

        accountRequest = new CreateAccountRequestDTO(1L,"Savings", new BigDecimal(100));
    }

    @Test
    void retrieveAllAccountsTest() {
        //Mock the service method
        when(accountService.retrieveAllAccounts())
                .thenReturn(accountResponses); //returns list of AccountResponsweDTO

        //Create request
        RequestBuilder request = MockMvcRequestBuilders
                .get("/account")
                .accept(MediaType.APPLICATION_JSON);

       //Perform the GET request and get result
        MvcResult result;
        try {
            result = mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
            //Verify the Response
            String expectedResponse = "[{\"accountNumber\":1234," +
                    "\"sortCode\":1111," +
                    "\"name\":\"Savings\"," +
                    " \"balance\":100, \"transactions\":[]," +
                    " \"openingBalance\":100," +
                    "\"customer\": 1}]";
            assertEquals (expectedResponse, result.getResponse().getContentAsString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAccountByNumber(){
        when(accountService.findAccountByNumber(accountResponse1.number())).thenReturn(accountResponse1);

        //Create Request
        RequestBuilder request = MockMvcRequestBuilders
                .get("/account/{number}")
                .accept(MediaType.APPLICATION_JSON);

        //Perform Request
        MvcResult result;
        try{
            result = mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
           // Verify Response
            String expectedResponse ="[{\"accountNumber\":1234," +
                    "\"sortCode\":1111," +
                    "\"name\":\"Savings\", " +
                    "\"balance\":100, \"transactions\":[]" +
                    ", \"openingBalance\":100," +
                    "\"customer\": 1}]";

                    assertEquals(expectedResponse, result.getResponse().getContentAsString());

        } catch (Exception e){
            throw new RuntimeException(e);

        }

@Test
        void createAccount(){
            when(accountService.createAccount(accountRequest)).thenReturn(accountResponse1);

            //Create request
        RequestBuilder request = MockMvcRequestBuilders
                .get("/account")
                .accept(MediaType.APPLICATION_JSON);

        //Perform request
        MvcResult result;

        try{
            result = mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            //Verify Response
            String expectedResponse ="[{\"accountNumber\":1234," +
                    "\"sortCode\":1111," +
                    "\"name\":\"Savings\", " +
                    "\"balance\":100, \"transactions\":[]" +
                    ", \"openingBalance\":100," +
                    "\"customer\": 1}]";

            assertEquals(expectedResponse, result.getResponse().getContentAsString());

        } catch (Exception e){
            throw new RuntimeException(e);

        }

//@Test
//        void closeAccount(){
//
//        }
//    }
//}
