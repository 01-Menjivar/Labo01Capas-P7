package DTO;

import java.time.LocalDate;

public class DoctorDTO extends PersonDTO {
    private LocalDate recruitmentDate;
    private String specialty;
    private String code;

    public DoctorDTO(String name, String surname, String dui, LocalDate dateOfBirth,
                     LocalDate recruitmentDate, String specialty, String code) {
        super(name, surname, dui, dateOfBirth);
        this.recruitmentDate = recruitmentDate;
        this.specialty = specialty;
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

    public void setCode(String code) {
        this.code = code;
    }
}