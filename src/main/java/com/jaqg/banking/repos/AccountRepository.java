//package com.jaqg.banking.repos;
//
//import com.jaqg.banking.entities.Account;
//import com.jaqg.banking.entities.LocalAccount;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface AccountRepository extends JpaRepository<Account, Long> {
//
//    List<LocalAccount> findBySortCodeAndIsClosedFalse(int sortCode);
//
//    Optional<Account> findByIdNumberAndIdSortCodeAndIsClosedFalse(long id, int sortCode);
//
//}
