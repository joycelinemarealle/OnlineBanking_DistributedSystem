package com.jaqg.banking.dto;

import com.jaqg.banking.entities.Account;

import java.util.List;

public record CustomerPostRequest(Long ID, String name, List<Account> accounts) {}
