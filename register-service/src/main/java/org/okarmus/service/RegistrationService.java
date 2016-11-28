package org.okarmus.service;

import org.okarmus.client.auth.AuthServiceClient;
import org.okarmus.client.user.UserServiceClient;
import org.okarmus.domain.RegistrationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mateusz on 26.11.16.
 */
@Service
public class RegistrationService {

    private final AuthServiceClient authClient;
    private final UserServiceClient userClient;

    @Autowired
    public RegistrationService(AuthServiceClient authClient, UserServiceClient userClient) {
        this.authClient = authClient;
        this.userClient = userClient;
    }

    public String register(RegistrationData registrationData) {

        String authResult = authClient.createAccount(registrationData.getLoginData());

        String userResult = userClient.addUser(registrationData.getUserData());

        System.out.println("Registration should be done here");

        return authResult + " " + userResult;
    }
}
