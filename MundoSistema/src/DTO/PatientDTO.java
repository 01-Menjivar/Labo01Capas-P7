package DTO;

import java.time.LocalDate;

public class PatientDTO extends PersonDTO {
    public PatientDTO(String name, String surname, String dui, LocalDate dateOfBirth) {
        super(name, surname, dui, dateOfBirth);
    }
}
