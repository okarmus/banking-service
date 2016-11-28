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

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }
}
