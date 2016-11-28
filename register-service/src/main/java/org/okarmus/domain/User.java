package org.okarmus.domain;

/**
 * Created by mateusz on 17.11.16.
 */
public class User {

    private String login;
    private PersonalInfo personalInfo;
    private ContactInfo contactInfo;
    private Address address;

    public String getLogin() {
        return login;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public Address getAddress() {
        return address;
    }
}
