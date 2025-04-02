package Repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Entities.Appointment;
import Entities.Doctor;
import Entities.Patient;

public class AppointmentRepository {
    private List<Appointment> appointments = new ArrayList<>();

    public void save(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> findAll() {
        return new ArrayList<>(appointments);
    }

    public List<Appointment> findAllSorted() {
        return appointments.stream()
                .sorted(Comparator.comparing(Appointment::getDate)
                        .thenComparing(Appointment::getTime))
                .collect(Collectors.toList());
    }

    public List<Appointment> findByDoctorCode(String doctorCode) {
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().getCode().equals(doctorCode))
                .collect(Collectors.toList());
    }

    public List<Appointment> findByDate(LocalDate date) {
        return appointments.stream()
                .filter(appointment -> appointment.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Appointment> findByDoctorAndDateRange(Doctor doctor, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().equals(doctor))
                .filter(appointment -> appointment.getDate().equals(date))
                .filter(appointment -> !appointment.getTime().isBefore(startTime) && !appointment.getTime().isAfter(endTime))
                .collect(Collectors.toList());
    }

    public List<Appointment> findByPatientAndDateRange(Patient patient, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return appointments.stream()
                .filter(appointment -> appointment.getPatient().equals(patient))
                .filter(appointment -> appointment.getDate().equals(date))
                .filter(appointment -> !appointment.getTime().isBefore(startTime) && !appointment.getTime().isAfter(endTime))
                .collect(Collectors.toList());
    }

    public void delete(Appointment appointment) {
        appointments.remove(appointment);
    }
}

