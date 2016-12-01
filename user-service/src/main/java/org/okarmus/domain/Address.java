package org.okarmus.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by mateusz on 17.11.16.
 */
public class Address {

    @NotNull
    @Min(2)
    private String country;
    @NotNull
    @Min(2)
    private String city;
    @NotNull
    @Min(2)
    private String street;
    @NotNull
    @Min(2)
    private String streetNo;
    @NotNull
    @Size(min = 6, max = 6)
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
