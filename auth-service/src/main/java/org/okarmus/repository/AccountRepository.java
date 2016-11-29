package org.okarmus.repository;

import org.okarmus.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by mateusz on 22.11.16.
 */
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByLogin(String login);
}
