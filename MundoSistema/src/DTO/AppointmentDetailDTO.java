package DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppointmentDetailDTO {
    private PatientDTO patient;
    private DoctorDTO doctor;
    private LocalDate date;
    private LocalTime time;
    private String specialty;
    private boolean attendance;

    public AppointmentDetailDTO(PatientDTO patient, DoctorDTO doctor, LocalDate date,
                                LocalTime time, String specialty, boolean attendance) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.specialty = specialty;
        this.attendance = attendance;
    }

    public String getFormattedDate() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getFormattedTime() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getAttendanceStatus() {
        return attendance ? "Asistió" : "No asistió";
    }

    public String getPatientFullName() {
        return patient.getName() + " " + patient.getSurname();
    }

    public String getDoctorFullName() {
        return doctor.getName() + " " + doctor.getSurname();
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
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
