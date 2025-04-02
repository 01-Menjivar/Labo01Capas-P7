package Entities;

import java.time.LocalDate;

public class Doctor extends Person {
    LocalDate recruitmentDate;
    String specialty;
    String code;

    public Doctor(String name, String surname, String DUI, LocalDate dateOfBirth,
                  String recruitmentDateStr, String specialty, String code) {
        super(name, surname, DUI, dateOfBirth);
        this.recruitmentDate = LocalDate.parse(recruitmentDateStr);
        this.specialty = specialty;
        setCode(code);
    }

    public void setCode(String code) {
        if (!code.matches("ZNH-\\d[A-Za-z]\\d-MD-[A-Za-z]\\d")) {
            throw new IllegalArgumentException("Invalid doctor code format. Must be ZNH-XAX-MD-AX");
        }
        this.code = code;
    }

    public LocalDate getRecruitmentDate() {
        return recruitmentDate;
    }

    public void setRecruitmentDate(LocalDate recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getCode() {
        return code;
    }

}
