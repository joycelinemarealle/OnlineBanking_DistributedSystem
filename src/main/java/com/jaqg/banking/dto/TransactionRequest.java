package com.jaqg.banking.dto;

import java.math.BigDecimal;

public record TransactionRequest (
     String type,
     int fromAcount,
     int fromAcountSortCode,
     int toAcount,
     int toAcountSortCode,
     BigDecimal amount){}
