package org.okarmus.service.create;

import org.okarmus.domain.Account;
import org.okarmus.exception.UserExistsException;
import org.okarmus.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.*;

/**
 * Created by mateusz on 22.11.16.
 */
@Service
public class AccountCreateService {

    @Autowired
    private AccountRepository repository;

    @Value("${exception.message.userAlreadyExist}")
    private String exceptionMessage;

    public void create(Account account) {               //TODO maybe password should be hashed
        checkIfLoginUnique(account);
        saveAccount(account);
    }

    private void checkIfLoginUnique(Account account) {
        repository.findByLogin(account.getLogin())
                  .ifPresent(a -> { throw new UserExistsException(format(exceptionMessage, a.getLogin()));});
    }

    private void saveAccount(Account account) {
        repository.save(account);
    }
}
