package org.okarmus.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.okarmus.AuthServiceApplication
import org.okarmus.domain.Account
import org.okarmus.repository.AccountRepository
import org.okarmus.service.create.AccountCreateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by mateusz on 29.11.16.
 */

@ContextConfiguration(classes =  [AuthServiceApplication.class])
@WebAppConfiguration
@TestPropertySource(properties = ["exception.message.userNotExist = User %s does not exist", "exception.message.userAlreadyExist = User %s already exist"])
class AccountExceptionHandlerTest extends Specification {

    @Autowired
    AccountCreateService accountCreateService
    @Autowired
    WebApplicationContext wac

    MockMvc mockMvc
    AccountRepository accountRepository = Mock()

    def login = "acme"
    def password = "acmesecret"

    def setup() {
        accountCreateService.repository = accountRepository
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    def "should return exception from advice"() {
        given:
            Account account = new Account(login, password, true)
            accountRepository.findByLogin(login) >> Optional.of(new Account(login, "otherPassword", true))
        when:
            MvcResult result = mockMvc.perform(post("/account")
                                    .contentType(APPLICATION_JSON_UTF8)
                                    .content(convertObjectToJsonBytes(account)))
                                    .andExpect(status().isConflict())
                                    .andReturn()
        then:
            result.getResponse().getContentAsString() == "User acme already exist"
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }
}
