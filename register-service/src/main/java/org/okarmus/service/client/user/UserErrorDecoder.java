package org.okarmus.service.client.user;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.okarmus.service.client.exception.InvalidUserDataException;
import org.okarmus.service.client.response.BodyExtractor;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by mateusz on 29.11.16.
 */
public class UserErrorDecoder implements ErrorDecoder {

    private final Default defaultDecoder;
    private final BodyExtractor bodyExtractor;

    @Autowired
    public UserErrorDecoder(Default defaultDecoder, BodyExtractor bodyExtractor) {
        this.defaultDecoder = defaultDecoder;
        this.bodyExtractor = bodyExtractor;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        if (validationError(response)) {
            requestValidationException(response);
        }
        return useDefaultDecoder(methodKey, response);
    }

    private boolean validationError(Response response) {
        return response.status() == BAD_REQUEST.value();
    }

    private void requestValidationException(Response response) {
        String errorMessage = bodyExtractor.extract(response);
        throw new InvalidUserDataException(errorMessage);
    }

    private Exception useDefaultDecoder(String methodKey, Response response) {
        return defaultDecoder.decode(methodKey, response);
    }
}