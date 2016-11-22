package org.okarmus.service.create;

import org.okarmus.domain.Account;
import org.okarmus.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by mateusz on 22.11.16.
 */
@Service
public class AccountCreateService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private AccountRepository repository;

    public void create(Account account) {
        repository.findByLogin(account.getLogin())      //TODO we are checking if user with this login already exists
                  .ifPresent(account1 ->  new RuntimeException("There is a problem because user exists"));

        Account encodedAccount = encodePassword(account);
        repository.save(encodedAccount);
    }

    private Account encodePassword(Account account) {
        String encodedPwd = encoder.encode(account.getPassword());
        return new Account(account.getLogin(), encodedPwd, account.isActive());

    }


}
