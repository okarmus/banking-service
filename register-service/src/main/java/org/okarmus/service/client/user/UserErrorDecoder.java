package org.okarmus.service.client.user;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Created by mateusz on 29.11.16.
 */
public class UserErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return null;
    }
}