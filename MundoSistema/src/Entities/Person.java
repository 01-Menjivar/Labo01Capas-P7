package Entities;

import java.time.LocalDate;

public abstract class Person {
    private String name;
    private String surname;
    private String DUI;
    private LocalDate dateOfBirth;

    public Person(String name, String surname, String DUI, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.DUI = DUI;
        this.dateOfBirth = dateOfBirth;
    }

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

    public String getDUI() {
        return DUI;
    }

    public void setDUI(String DUI) {
        this.DUI = DUI;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", DUI='" + DUI + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
