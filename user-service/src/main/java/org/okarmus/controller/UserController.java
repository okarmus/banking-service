package org.okarmus.controller;

import org.okarmus.domain.User;
import org.okarmus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> addUser(@RequestBody User user) {
        String persistedId = userRepository.save(user).getId();
        return new ResponseEntity<>(persistedId, CREATED);
    }

    @RequestMapping("/{userId}")
    public ResponseEntity<User> retrieveUse(@RequestParam String userId) {  //TODO what if user does not exist
        User user = userRepository.findOne(userId);
        return new ResponseEntity<>(user, OK);
    }
}
