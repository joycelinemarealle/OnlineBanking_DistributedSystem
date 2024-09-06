package com.jaqg.banking.repository;

import com.jaqg.banking.entity.LocalAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalAccountRepository extends JpaRepository<LocalAccount, Long> {

    List<LocalAccount> findByIdSortCodeAndIsClosedFalse(Integer sortCode);

    Optional<LocalAccount> findByIdNumberAndIdSortCodeAndIsClosedFalse(Long id, Integer sortCode);

}
