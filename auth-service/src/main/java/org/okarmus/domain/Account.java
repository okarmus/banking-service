package org.okarmus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by mateusz on 22.11.16.
 */

@Entity
public class Account {

    @Id @GeneratedValue
    private long id;

    private String login, password;
    private boolean active;

    public Account() {} //why jpa why??

    public Account(String login, String password, boolean active) {
        this.login = login;
        this.password = password;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }
}
