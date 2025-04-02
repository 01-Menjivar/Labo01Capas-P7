package DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {
    private String patientName;
    private String patientSurname;
    private String patientDui;
    private String doctorName;
    private String doctorSurname;
    private String doctorCode;
    private LocalDate date;
    private LocalTime time;
    private String specialty;
    private boolean attendance;

    public AppointmentDTO(String patientName, String patientSurname, String patientDui,
                          String doctorName, String doctorSurname, String doctorCode,
                          LocalDate date, LocalTime time, String specialty, boolean attendance) {
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.patientDui = patientDui;
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.doctorCode = doctorCode;
        this.date = date;
        this.time = time;
        this.specialty = specialty;
        this.attendance = attendance;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public String getPatientDui() {
        return patientDui;
    }

    public void setPatientDui(String patientDui) {
        this.patientDui = patientDui;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }
}