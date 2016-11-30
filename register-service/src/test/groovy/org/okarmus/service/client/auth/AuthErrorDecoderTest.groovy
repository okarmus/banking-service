package org.okarmus.service.client.auth

import feign.Response
import feign.codec.ErrorDecoder
import org.okarmus.service.client.auth.response.BodyExtractor
import org.okarmus.service.client.exception.ClientExistsException
import spock.lang.Specification

import static java.nio.charset.StandardCharsets.UTF_8

/**
 * Created by mateusz on 30.11.16.
 */
class AuthErrorDecoderTest extends Specification {

    AuthErrorDecoder underTest

    ErrorDecoder.Default defaultDecoder = Mock()
    BodyExtractor bodyExtractor = new BodyExtractor()

    def errorMessage = "This is a sample error message"

    def setup() {
        underTest = new AuthErrorDecoder(defaultDecoder, bodyExtractor)
    }

    def "should return client exist exception when there is a conflict" () {
        given:
            Response.Body body = Mock()
            body.asInputStream()  >> new ByteArrayInputStream(errorMessage.getBytes(UTF_8));
            Response response = new Response(new Response.Builder(body: body, status: 409, headers: new HashMap<String, Collection<String>>()))
        when:
            Exception returnedEx = underTest.decode("sampleKey", response)
        then:
            returnedEx instanceof  ClientExistsException
            returnedEx.getMessage() == errorMessage
    }

    def "should use default decoder for status other than confict"() {
        given:
            def key = "sampleKey"
            Response.Body body = Mock()
            body.asInputStream()  >> new ByteArrayInputStream(errorMessage.getBytes(UTF_8));
            Response response = new Response(new Response.Builder(body: body, status: 400, headers: new HashMap<String, Collection<String>>()))

        when:
            underTest.decode(key, response)
        then:
            1 * defaultDecoder.decode(key, response)
    }

}
