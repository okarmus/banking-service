package org.okarmus.service.client.exception;

/**
 * Created by mateusz on 08.12.16.
 */
public class InvalidUserDataException extends RuntimeException {
    public InvalidUserDataException(String message) {
        super(message);
    }
}
