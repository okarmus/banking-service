package org.okarmus.domain;

/**
 * Created by mateusz on 17.11.16.
 */
public class Address {

    private String country;
    private String city;
    private String street;
    private String streetNo;    //todo this should be value object
    private String postalCode;  //todo this should be value object

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

        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (streetNo != null ? !streetNo.equals(address.streetNo) : address.streetNo != null) return false;
        return postalCode != null ? postalCode.equals(address.postalCode) : address.postalCode == null;

    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (streetNo != null ? streetNo.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }
}
