package org.okarmus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



/**
 * Created by mateusz on 17.11.16.
 */

@Document
public class User {
    @Id
    private String id;  //TODO maybe this id should be different
    private String login;

    private PersonalInfo personalInfo;
    private ContactInfo contactInfo;
    private Address address;

    public String getId() {
        return id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!login.equals(user.login)) return false;
        if (!personalInfo.equals(user.personalInfo)) return false;
        if (!contactInfo.equals(user.contactInfo)) return false;
        return address.equals(user.address);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + personalInfo.hashCode();
        result = 31 * result + contactInfo.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
