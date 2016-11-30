package org.okarmus.service;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.okarmus.service.client.auth.FeignAuthClient;
import org.okarmus.service.client.exception.ClientExistsException;
import org.okarmus.service.client.user.UserServiceClient;
import org.okarmus.domain.RegistrationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by mateusz on 26.11.16.
 */
@Service
public class RegistrationService {

    private final FeignAuthClient authClient;
    private final UserServiceClient userClient;

    @Autowired
    public RegistrationService(FeignAuthClient authClient, UserServiceClient userClient) {
        this.authClient = authClient;
        this.userClient = userClient;
    }

    public String register(RegistrationData registrationData) {
        try {
            authClient.createAccount(registrationData.getLoginData());
            String userResult = userClient.addUser(registrationData.getUserData());
            return userResult;

        }catch (HystrixRuntimeException ex) {
            if (ex.getCause() instanceof ClientExistsException) {   //TODO handle situation when client with id exists
                return ex.getCause().getMessage();
            }
            //TODO we should handle situation when client was added to auth but hasent been to user
            //TODO so it should be reverted from auth

            throw ex;
        }
    }
}
