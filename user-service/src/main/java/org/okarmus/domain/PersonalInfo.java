package org.okarmus.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by mateusz on 17.11.16.
 */
public class PersonalInfo {
    @NotNull(message = "Name can not be null")
    private String name;
    @NotNull(message = "Surname can not be null")
    private String surname;

    @Min(value = 7, message = "Pesel must have at least 7 characters")
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
