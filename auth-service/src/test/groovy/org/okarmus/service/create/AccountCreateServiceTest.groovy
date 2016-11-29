package org.okarmus.service.create

import org.okarmus.domain.Account
import org.okarmus.exception.UserExistsException
import org.okarmus.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

/**
 * Created by mateusz on 28.11.16.
 */
@TestPropertySource(properties = ["exception.message.userAlreadyExist = User with login %s already exist"])
@ContextConfiguration(classes = [AccountCreateServiceTestConfiguration.class])
class AccountCreateServiceTest extends Specification {

    @Autowired
    AccountCreateService underTest

    AccountRepository repository = Mock()

    def login = "acme"
    private account = new Account(login, "acmesecret", true)

    def setup() {
        underTest.repository = repository
    }

    def "should return error when login is not unique"() {
        setup:
            repository.findByLogin(login) >> Optional.of(new Account("acme", "anothersecret", false))
        when:
            underTest.create(this.account)
        then:
            UserExistsException ex = thrown()
            ex.message == "User with login acme already exist"
    }

    def "should save account when data are correct" () {
        given:
            repository.findByLogin(login) >> Optional.empty()
        when:
            underTest.create(account)
        then:
            1 * repository.save(account)
    }
}
