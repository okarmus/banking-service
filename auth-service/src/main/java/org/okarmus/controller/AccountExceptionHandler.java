package org.okarmus.controller;

import org.okarmus.exception.UserExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * Created by mateusz on 29.11.16.
 */
@ControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<String> handleUserExistsException(HttpServletRequest request, Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }
}
