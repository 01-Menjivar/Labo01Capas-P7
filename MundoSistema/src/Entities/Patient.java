package Entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Patient extends Person {
    private static final String DEFAULT_DUI = "00000000-0";

    public Patient(String name, String surname, String DUI, LocalDate dateOfBirth) {
        super(name, surname, validateDUI(DUI, String.valueOf(dateOfBirth)), dateOfBirth);
    }

    private static String validateDUI(String DUI, String dateOfBirth) {
        if (isMinor(dateOfBirth)) {
            return DEFAULT_DUI;
        }
        return DUI;
    }

    private static boolean isMinor(String dateOfBirthStr) {
        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirthStr);
            return Period.between(birthDate, LocalDate.now()).getYears() < 18;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd", e);
        }
    }

}