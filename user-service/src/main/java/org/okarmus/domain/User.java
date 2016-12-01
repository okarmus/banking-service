package org.okarmus.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


/**
 * Created by mateusz on 17.11.16.
 */

@Document
public class User {
    @Id
    private String id;
    @NotNull(message = "login can not be null")
    @Length(min = 4, message = "login must have at least 3 characters" )
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
        return new EqualsBuilder()
                .append(user, user.id)
                .append(login, user.login)
                .append(personalInfo, user.personalInfo)
                .append(contactInfo, user.contactInfo)
                .append(address, user.address)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(login)
                .append(personalInfo)
                .append(contactInfo)
                .append(address)
                .toHashCode();
    }
}
