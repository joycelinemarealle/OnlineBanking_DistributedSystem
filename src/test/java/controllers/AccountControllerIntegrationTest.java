package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.UnsupportedEncodingException;
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

    @Autowired
   private  ObjectMapper objectMapper;

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
        //Mock the service
        when(accountService.retrieveAllAccounts())
                .thenReturn(accountResponses); //returns list of AccountResponsweDTO

        //Create GET request
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
    void findAccountByNumber() throws Exception {

        //Mock Service
        when(accountService.findAccountByNumber(accountResponse1.number())).thenReturn(accountResponse1);

        //Create GET Request
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
    }

@Test
        void createAccount() throws Exception {
        //Mock Service
    when(accountService.createAccount(accountRequest)).thenReturn(accountResponse1);

            //Convert DTO to JSON
       String  accountRequestJson = objectMapper.writeValueAsString(accountRequest);

            //Create POST request
            RequestBuilder request = MockMvcRequestBuilders
                    .post("/account")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(accountRequestJson);

            //Perform request and get response
            MvcResult result;

            try {
                result = mockMvc.perform(request)
                        .andDo(print())
                        .andExpect(status().isCreated()) //expect 201 created
                        .andReturn();

                //Verify Response
                assertEquals(accountRequestJson, result.getResponse().getContentAsString());

            } catch (Exception e) {
                throw new RuntimeException(e);

            }
        }


    @Test
    void closeAccount() throws Exception {
        Long accountNumber = accountResponse1.number();
        BigDecimal balance = accountResponse1.balance();

        //Mock Service
        when(accountService.closeAccount(accountNumber)).thenReturn(balance);

        //Create DELETE Request
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/account/{number}", accountNumber)
                .accept(MediaType.APPLICATION_JSON);

        //Perform request and get response
        MvcResult result;

        try { result = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk()) //200 OK Status
                .andReturn();

        } catch(Exception e){
            throw new RuntimeException();
        }

        //Verify Response
        String expectedBalance = balance.toString();
        assertEquals(expectedBalance, result.getResponse().getContentAsString());

       }

}



