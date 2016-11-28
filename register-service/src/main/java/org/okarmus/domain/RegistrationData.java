package org.okarmus.domain;

/**
 * Created by mateusz on 26.11.16.
 */
public class RegistrationData {

    private User userData;
    private Account loginData;

    public RegistrationData(User userData, Account loginData) {
        this.userData = userData;
        this.loginData = loginData;
    }

    public RegistrationData() {}

    public User getUserData() {
        return userData;
    }

    public Account getLoginData() {
        return loginData;
    }
}
