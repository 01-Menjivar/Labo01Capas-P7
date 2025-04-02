package DTO;

import java.time.LocalDate;

public class PersonDTO {

        private String name;
        private String surname;
        private String dui;
        private LocalDate dateOfBirth;

        public PersonDTO(String name, String surname, String dui, LocalDate dateOfBirth) {
            this.name = name;
            this.surname = surname;
            this.dui = dui;
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

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
