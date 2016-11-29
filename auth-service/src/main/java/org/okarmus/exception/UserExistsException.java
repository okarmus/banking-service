package org.okarmus.exception;

/**
 * Created by mateusz on 28.11.16.
 */
public class UserExistsException extends RuntimeException{

    public UserExistsException(String message) {
        super(message);
    }
}
