package com.jaqg.banking.entities;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customers")
public class Customer {

    @Column(name = "full_Name")
    private String fullName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long ID;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    private boolean isRemoved = false;

    public Customer(String fullName) {
        this.fullName = fullName;
    }

    public Customer(){

    }

    public Customer(Long id, String name, List<Account> accounts) {
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
