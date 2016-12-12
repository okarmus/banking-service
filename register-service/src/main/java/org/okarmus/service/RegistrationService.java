package org.okarmus.service;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.okarmus.domain.Account;
import org.okarmus.domain.RegistrationData;
import org.okarmus.domain.User;
import org.okarmus.service.client.auth.FeignAuthClient;
import org.okarmus.service.client.exception.ClientExistsException;
import org.okarmus.service.client.exception.InvalidUserDataException;
import org.okarmus.service.client.user.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mateusz on 26.11.16.
 */
@Service
public class RegistrationService {      //TODO this must be tested

    private final FeignAuthClient authClient;
    private final UserServiceClient userClient;

    @Autowired
    public RegistrationService(FeignAuthClient authClient, UserServiceClient userClient) {
        this.authClient = authClient;
        this.userClient = userClient;
    }

    public String register(RegistrationData registrationData) {
        long id = 0;
        try {
            id = createAuthAccount(registrationData.getLoginData());
            return createUserAccount(registrationData.getUserData());

        } catch (ClientExistsException ex) {                    //TODO this is ugly
            return ex.getMessage();
        } catch (InvalidUserDataException ex) {
            authClient.deleteAccount(id);
            return ex.getMessage();
        } catch (Throwable ex) {
            return ex.getMessage();
        }
    }

    public long createAuthAccount(Account loginData) throws Throwable {
        try {
            return authClient.createAccount(loginData).getBody();
        } catch (HystrixRuntimeException ex) {
            throw ex.getCause();
        }
    }

    public String createUserAccount(User userData) throws Throwable {
        try {
            return userClient.addUser(userData);
        } catch (HystrixRuntimeException ex) {
            throw ex.getCause();
        }
    }
}
