package org.okarmus.domain;

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

        ContactInfo that = (ContactInfo) o;

        if (phoneNumber != that.phoneNumber) return false;
        return email != null ? email.equals(that.email) : that.email == null;

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + phoneNumber;
        return result;
    }
}
