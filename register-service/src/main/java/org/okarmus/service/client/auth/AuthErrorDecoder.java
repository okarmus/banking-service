package org.okarmus.service.client.auth;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.okarmus.service.client.response.BodyExtractor;
import org.okarmus.service.client.exception.ClientExistsException;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * Created by mateusz on 29.11.16.
 */
public class AuthErrorDecoder implements ErrorDecoder {

    private final Default defaultDecoder;
    private final BodyExtractor bodyExtractor;

    @Autowired
    public AuthErrorDecoder(Default defaultDecoder, BodyExtractor bodyExtractor) {
        this.defaultDecoder = defaultDecoder;
        this.bodyExtractor = bodyExtractor;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        if (clientExists(response)) {
            return clientExistException(response);
        }
        return useDefaultDecoder(methodKey, response);
    }

    private boolean clientExists(Response response) {
        return response.status() == CONFLICT.value();
    }

    private Exception clientExistException(Response response) {
        String errorMessage = bodyExtractor.extract(response);
        return new ClientExistsException(errorMessage);
    }

    private Exception useDefaultDecoder(String methodKey, Response response) {
        return defaultDecoder.decode(methodKey, response);
    }
}
