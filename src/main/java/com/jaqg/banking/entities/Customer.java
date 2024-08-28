package com.jaqg.banking.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 150, nullable = false)
    private String fullName;

    private boolean isRemoved = false;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private final List<Account> accounts = new ArrayList<>();

    public Customer(String fullName) {
        this.fullName = fullName;
    }

    public Customer() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

}
