//package com.jaqg.banking.repos;
//
//import com.jaqg.banking.entities.Account;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class LocalAccountRepository implements AccountRepository{
//    private List<Account> accounts;
//
//    public LocalAccountRepository (){
//        this.accounts = new ArrayList<>();
//        //connect to database H2 develop then post-gress in produc?????
//    }
//
//    @Override
//    public List<Account> getAllAccounts() {
//        //use jpa template
//
//        return accounts;
//    }
//
//    @Override
//    public Account findAccountByNumber(long number) {
//        //use jpa template
//
//        return null;
//    }
//
//    @Override
//    public void saveAccount(Account account) {
//        accounts.add(account);
//        //use jpa template
//    }
//
//    @Override
//    public void batchInsert(List<Account> accounts) {
//        //using jpa template
//    }
//}
