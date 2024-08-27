package com.jaqg.banking.dto;

import java.math.BigDecimal;

public record TransactionRequest (
     String type,
     Long fromAcount,
     Long fromAccountSortCode,
     Long toAcount,
     Long toAcountSortCode,
     BigDecimal amount){}
