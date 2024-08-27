package com.jaqg.banking.DTO;

import com.jaqg.banking.entities.Account;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public record CustomerGetRequest (Long ID,String name,List<Account> accounts){}


