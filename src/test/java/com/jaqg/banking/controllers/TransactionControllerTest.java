package com.jaqg.banking.controllers;


import com.jaqg.banking.config.JacksonConfiguration;
import com.jaqg.banking.dto.TransactionRequest;
import com.jaqg.banking.dto.TransactionResponse;
import com.jaqg.banking.enums.OperationType;
import com.jaqg.banking.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Import(JacksonConfiguration.class)
@WebMvcTest(controllers = TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void createDeposit() {
        final TransactionResponse transactionResponse = new TransactionResponse(
                LocalDateTime.of(2024, 5, 4, 12, 5, 6),
                OperationType.DEPOSIT,
                null,
                null,
                1L,
                1234,
                new BigDecimal("55.65")
        );

        final String payload = "{" +
                "  \"type\": \"" + transactionResponse.type() + "\"" +
                ", \"fromAccount\": " + transactionResponse.fromAccount() +
                ", \"fromAccountSortCode\": " + transactionResponse.fromAccountSortCode() +
                ", \"toAccount\": " + transactionResponse.toAccount() +
                ", \"toAccountSortCode\": " + transactionResponse.toAccountSortCode() +
                ", \"amount\": " + transactionResponse.amount() +
                "}";

        when(transactionService.deposit(any(TransactionRequest.class))).thenReturn(transactionResponse);

        try {
            mockMvc.perform(post("/transaction")
                            .content(payload)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("time").value("04-05-2024 12:05:06"))
                    .andExpect(jsonPath("type").value(transactionResponse.type().name()))
                    .andExpect(jsonPath("fromAccount").value(transactionResponse.fromAccount()))
                    .andExpect(jsonPath("fromAccountSortCode").value(transactionResponse.fromAccountSortCode()))
                    .andExpect(jsonPath("toAccount").value(transactionResponse.toAccount()))
                    .andExpect(jsonPath("toAccountSortCode").value(transactionResponse.toAccountSortCode()))
                    .andExpect(jsonPath("amount").value(transactionResponse.amount()))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createWithdrawal() {
        final TransactionResponse transactionResponse = new TransactionResponse(
                LocalDateTime.of(2024, 5, 4, 12, 5, 6),
                OperationType.WITHDRAWAL,
                1L,
                1234,
                null,
                null,
                new BigDecimal("55.65")
        );

        final String payload = "{" +
                "  \"type\": \"" + transactionResponse.type() + "\"" +
                ", \"fromAccount\": " + transactionResponse.fromAccount() +
                ", \"fromAccountSortCode\": " + transactionResponse.fromAccountSortCode() +
                ", \"toAccount\": " + transactionResponse.toAccount() +
                ", \"toAccountSortCode\": " + transactionResponse.toAccountSortCode() +
                ", \"amount\": " + transactionResponse.amount() +
                "}";

        when(transactionService.withdraw(any(TransactionRequest.class))).thenReturn(transactionResponse);

        try {
            mockMvc.perform(post("/transaction")
                            .content(payload)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("time").value("04-05-2024 12:05:06"))
                    .andExpect(jsonPath("type").value(transactionResponse.type().name()))
                    .andExpect(jsonPath("fromAccount").value(transactionResponse.fromAccount()))
                    .andExpect(jsonPath("fromAccountSortCode").value(transactionResponse.fromAccountSortCode()))
                    .andExpect(jsonPath("toAccount").value(transactionResponse.toAccount()))
                    .andExpect(jsonPath("toAccountSortCode").value(transactionResponse.toAccountSortCode()))
                    .andExpect(jsonPath("amount").value(transactionResponse.amount()))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createTransfer() {
        final TransactionResponse transactionResponse = new TransactionResponse(
                LocalDateTime.of(2024, 5, 4, 12, 5, 6),
                OperationType.TRANSFER,
                1L,
                1234,
                2L,
                1234,
                new BigDecimal("55.65")
        );

        final String payload = "{" +
                "  \"type\": \"" + transactionResponse.type() + "\"" +
                ", \"fromAccount\": " + transactionResponse.fromAccount() +
                ", \"fromAccountSortCode\": " + transactionResponse.fromAccountSortCode() +
                ", \"toAccount\": " + transactionResponse.toAccount() +
                ", \"toAccountSortCode\": " + transactionResponse.toAccountSortCode() +
                ", \"amount\": " + transactionResponse.amount() +
                "}";

        when(transactionService.executeTransfer(any(TransactionRequest.class))).thenReturn(transactionResponse);

        try {
            mockMvc.perform(post("/transaction")
                            .content(payload)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("time").value("04-05-2024 12:05:06"))
                    .andExpect(jsonPath("type").value(transactionResponse.type().name()))
                    .andExpect(jsonPath("fromAccount").value(transactionResponse.fromAccount()))
                    .andExpect(jsonPath("fromAccountSortCode").value(transactionResponse.fromAccountSortCode()))
                    .andExpect(jsonPath("toAccount").value(transactionResponse.toAccount()))
                    .andExpect(jsonPath("toAccountSortCode").value(transactionResponse.toAccountSortCode()))
                    .andExpect(jsonPath("amount").value(transactionResponse.amount()))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}