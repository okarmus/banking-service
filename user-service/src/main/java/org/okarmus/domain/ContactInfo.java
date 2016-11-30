package org.okarmus.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by mateusz on 17.11.16.
 */
public class ContactInfo {

    private String email;
    private int phoneNumber;

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactInfo contact = (ContactInfo) o;
        return new EqualsBuilder()
                .append(phoneNumber, contact.phoneNumber)
                .append(email, contact.email)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(email)
                .append(phoneNumber)
                .toHashCode();
    }
}
