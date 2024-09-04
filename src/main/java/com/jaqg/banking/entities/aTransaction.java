package com.jaqg.banking.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class aTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This helps auto-generate primary keys
    private long id;
}
