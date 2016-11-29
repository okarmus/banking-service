package org.okarmus.service.load

import org.okarmus.domain.Account
import org.okarmus.repository.AccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Specification

/**
 * Created by mateusz on 28.11.16.
 */
class AccountUserDetailsServiceTest extends Specification {

    AccountUserDetailsService underTest

    AccountRepository repository = Mock()
    String message = "User with login %s does not exist";

    def login = "acme"
    def password = "acmesecret"
    def active = true

    def setup() {
        underTest = new AccountUserDetailsService(accountRepository: repository, exceptionMessage: message)
    }

    def "should throw exception when user does not exist"() {
        given:
            repository.findByLogin(login) >> Optional.empty()
        when:
            underTest.loadUserByUsername(login)
        then:
            UsernameNotFoundException ex = thrown()
            ex.message == "User with login acme does not exist"
    }

    def "should return proper user object"() {
        given:
            Account account = new Account(login, password, active)
            repository.findByLogin(login) >> Optional.of(account)
        when:
            UserDetails user = underTest.loadUserByUsername(login)
        then:
            user.getUsername() == login
            user.getPassword() == password
            user.isEnabled()
            user.isAccountNonExpired()
            user.isCredentialsNonExpired()
            user.isAccountNonLocked()
            user.getAuthorities().size() == 2
    }
}
