package org.okarmus.domain;

/**
 * Created by mateusz on 28.11.16.
 */
public class Account {
    private String login;
    private String password;
    private boolean active;

    public Account() {}

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
        this.active = true;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
