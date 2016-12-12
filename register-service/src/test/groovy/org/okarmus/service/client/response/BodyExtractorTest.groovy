package org.okarmus.service.client.response

import feign.Response
import spock.lang.Specification

import static java.nio.charset.StandardCharsets.UTF_8

/**
 * Created by mateusz on 29.11.16.
 */
class BodyExtractorTest extends Specification {

    BodyExtractor underTest
    def errorMessage = "This is an example error messsage"

    def setup() {
        underTest = new BodyExtractor()
    }

    def "Should extract information from response"() {
        given:
            Response.Body body = Mock()
            body.asInputStream()  >> new ByteArrayInputStream(errorMessage.getBytes(UTF_8));
            Response response = new Response(new Response.Builder(body: body, status: 200, headers: new HashMap<String, Collection<String>>()))
        when:
            String actualMessage = underTest.extract(response)
        then:
            actualMessage == errorMessage
    }
}
