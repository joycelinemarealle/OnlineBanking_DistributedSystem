package com.jaqg.banking.controllers;

import com.jaqg.banking.dto.AccountResponseDTO;
import com.jaqg.banking.dto.CreateAccountRequestDTO;
import com.jaqg.banking.services.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/sortCode")
@CrossOrigin
public class BankController {

    private Integer sourceCode;

    public BankController(@Value("${sourcecode}") Integer sourceCode) {
        this.sourceCode = sourceCode;
    }

    @GetMapping
    public Integer getSourceCode(){
       return sourceCode;
    }
}

