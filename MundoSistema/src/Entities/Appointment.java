package Entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private LocalTime time;
    private String specialty;
    private boolean attendance;

    public Appointment(Patient patient, Doctor doctor, LocalDate date, LocalTime time, String specialty, boolean attendance) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.specialty = specialty;
        this.attendance = attendance;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
