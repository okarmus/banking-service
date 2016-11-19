package org.okarmus.domain;

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

        PersonalInfo that = (PersonalInfo) o;

        if (pesel != that.pesel) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return surname != null ? surname.equals(that.surname) : that.surname == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + pesel;
        return result;
    }
}
