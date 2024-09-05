package com.jaqg.banking.repos;

import com.jaqg.banking.entities.RemoteAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RemoteAccountRepository extends JpaRepository<RemoteAccount, Long> {

    Optional<RemoteAccount> findByIdNumberAndIdSortCode(long id, int sortCode);

}
