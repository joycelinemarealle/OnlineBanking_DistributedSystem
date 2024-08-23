package com.jaqg.banking.entities;
import jakarta.persistence.Id;


import java.util.ArrayList;
import java.util.List;

public class Customer {

    String fullName;

    @Id
    long ID;
    List<Account> accounts = new ArrayList<Account>();

    public Customer(String fullName, long ID) {
        this.fullName = fullName;
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getUniqueID() {
        return ID;
    }

    public void setUniqueID(int uniqueID) {
        this.ID = uniqueID;
    }


    public List<Account> getAccounts() {
        return accounts;
    }


}
