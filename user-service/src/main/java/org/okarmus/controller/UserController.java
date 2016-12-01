package org.okarmus.controller;

import org.okarmus.domain.User;
import org.okarmus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by mateusz on 17.11.16.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = POST)
    public ResponseEntity<String> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = extractErrorMessage(bindingResult);
            return new ResponseEntity<>(errorMessage, BAD_REQUEST);
        }

        String persistedId = userRepository.save(user).getId();
        return new ResponseEntity<>(persistedId, CREATED);
    }

    @RequestMapping("/{userId}")
    public ResponseEntity<User> retrieveUser(@PathVariable("userId") String userId) {  //TODO what if user does not exist
        User user = userRepository.findOne(userId);
        return new ResponseEntity<>(user, OK);
    }

    private String extractErrorMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(joining("\n"));
    }
}
