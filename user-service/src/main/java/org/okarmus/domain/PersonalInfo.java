package org.okarmus.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by mateusz on 17.11.16.
 */
public class PersonalInfo {
    private String name;
    private String surname;
    private int pesel;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPesel() {
        return pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalInfo personal = (PersonalInfo) o;
        return new EqualsBuilder()
                .append(pesel, personal.pesel)
                .append(name, personal.name)
                .append(surname, personal.surname)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(surname)
                .append(pesel)
                .toHashCode();
    }
}
