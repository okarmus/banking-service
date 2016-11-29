package org.okarmus.service.load;

import org.okarmus.domain.Account;
import org.okarmus.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * Created by mateusz on 22.11.16.
 */
@Service
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Value("${exception.message.userNotExist}")
    private String exceptionMessage;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(format(exceptionMessage, username)));

        return new User(account.getLogin(), account.getPassword(),
                account.isActive(), account.isActive(), account.isActive(), account.isActive(),
                AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER"));
    }
}
