package org.okarmus.service.client.exception;

/**
 * Created by mateusz on 29.11.16.
 */
public class ClientExistsException extends RuntimeException {
    public ClientExistsException(String message) {
        super(message);
    }
}