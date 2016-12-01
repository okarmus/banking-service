package org.okarmus.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.okarmus.UserServiceApplication
import org.okarmus.domain.Address
import org.okarmus.domain.ContactInfo
import org.okarmus.domain.PersonalInfo
import org.okarmus.domain.User
import org.okarmus.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8 as APPLICATION_JSON_UTF8
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by mateusz on 17.11.16.
 */

@ContextConfiguration(classes = [UserServiceApplication.class, EmbeddedMongoAutoConfiguration.class])
@TestPropertySource(properties = ["NotNull.user.login = Login is not provided"])
@WebAppConfiguration
class UserControllerTest extends Specification{

    MockMvc mockMvc

    @Autowired
    WebApplicationContext wac

    @Autowired
    UserRepository userRepository

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    def "should create user"() {
        given:
            User user = createSampleUser()

        when:
            MvcResult result = mockMvc.perform(post("/user")
                                      .contentType(APPLICATION_JSON_UTF8)
                                      .content(convertObjectToJsonBytes(user)))
                                      .andExpect(status().isCreated())
                                      .andReturn()
        and:
            def userId = result.getResponse().getContentAsString()
            def actualUser = userRepository.findOne(userId)

        then:
            actualUser.getId() != null
            actualUser.getLogin() == "sampleLogin"
            actualUser.getPersonalInfo() == user.getPersonalInfo()
            actualUser.getAddress() == user.getAddress()
            actualUser.getContactInfo() == user.getContactInfo()
    }

    def "should return exception when login is null"() {
        given:
            User user = createSampleUser()
            user.login = null
        when:
            MvcResult result = mockMvc.perform(post("/user")
                    .contentType(APPLICATION_JSON_UTF8)
                    .content(convertObjectToJsonBytes(user)))
                    .andExpect(status().isBadRequest())
                    .andReturn()
        then:
            result.getResponse().getContentAsString() == "login can not be null"
    }

    Object createSampleUser() {
        PersonalInfo personalInfo = new PersonalInfo(name: "Marek", surname: "Ostachiewicz", pesel: 900835643)
        ContactInfo contactInfo = new ContactInfo(email: "marek.ostachiewicz@gmail.com", phoneNumber: 603658456)
        Address address = new Address(country: "Poland", city: "Krakow", street: "Tyniecka", streetNo: "12/5", postalCode: "32-050")
        return new User(login: "sampleLogin",personalInfo: personalInfo, contactInfo: contactInfo, address: address)
    }


    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }

}