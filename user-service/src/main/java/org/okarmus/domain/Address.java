package org.okarmus.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by mateusz on 17.11.16.
 */
public class Address {

    private String country;
    private String city;
    private String street;
    private String streetNo;
    private String postalCode;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;
        return new EqualsBuilder()
                .append(country, address.country)
                .append(city, address.city)
                .append(street, address.street)
                .append(streetNo, address.streetNo)
                .append(postalCode, address.postalCode)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(country)
                .append(city)
                .append(street)
                .append(streetNo)
                .append(postalCode)
                .toHashCode();
    }
}
