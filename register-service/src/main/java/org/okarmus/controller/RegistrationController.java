package org.okarmus.controller;

import org.okarmus.service.client.auth.FeignAuthClient;
import org.okarmus.service.client.user.UserServiceClient;
import org.okarmus.domain.RegistrationData;
import org.okarmus.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by mateusz on 26.11.16.
 */

@RequestMapping("/registration")
@RestController
public class RegistrationController {

    private final RegistrationService service;

    @Autowired
    private FeignAuthClient authClient;

    @Autowired
    private UserServiceClient userClient;

    @Autowired
    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @RequestMapping(method = POST)
    public String register(@RequestBody RegistrationData registrationData) {
        return service.register(registrationData);
    }
}
