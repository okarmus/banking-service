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
    private PersonalInfo personalInfo;
    private ContactInfo contactInfo;
    private Address address;

    public String getId() {
        return id;
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

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        return personalInfo != null ? personalInfo.equals(user.personalInfo) : user.personalInfo == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (personalInfo != null ? personalInfo.hashCode() : 0);
        return result;
    }
}
