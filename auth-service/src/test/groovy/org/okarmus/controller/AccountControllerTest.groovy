package org.okarmus.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.okarmus.AuthServiceApplication
import org.okarmus.domain.Account
import org.okarmus.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by mateusz on 28.11.16.
 */

@ContextConfiguration(classes =  [AuthServiceApplication.class])
@WebAppConfiguration
@TestPropertySource(properties = ["exception.message.userNotExist = User %s does not exist", "exception.message.userAlreadyExist = User %s already exist"])
class AccountControllerTest extends Specification {

    MockMvc mockMvc

    @Autowired
    WebApplicationContext wac

    @Autowired
    AccountRepository repository

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    def "should create account"() {
        given:
            Account account = new Account("acme", "acmesecret", true)
        when:
            mockMvc.perform(post("/account")
                    .contentType(APPLICATION_JSON_UTF8)
                    .content(convertObjectToJsonBytes(account)))
                    .andExpect(status().isCreated())
        then:
            Account persisted = repository.findByLogin("acme").get()
            persisted.getId() != null
            persisted.getLogin() == "acme"
            persisted.getPassword() == "acmesecret"
            persisted.isActive()
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }
}
