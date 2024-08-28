package com.jaqg.banking.dto;

import com.jaqg.banking.entities.Account;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public record CustomerGetRequest (Long id,String fullName,List<Account> accounts){}


