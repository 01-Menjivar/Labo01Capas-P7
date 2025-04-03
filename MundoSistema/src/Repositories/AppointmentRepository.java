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
    //Se declara una lista de citas
    private List<Appointment> appointments = new ArrayList<>();

    //Method para guardar las citas
    public void save(Appointment appointment) {
        appointments.add(appointment);
    }

    //Method para devolver todas las citas
    public List<Appointment> findAll() {
        return new ArrayList<>(appointments);
    }

    //Method para devolver las citas pero filtradas por fecha y hora
    //hace uso de stream para usar .sorted y ordenarlas
    //luego se devuelve una lista con las citas ordenadas
    public List<Appointment> findAllSorted() {
        return appointments.stream()
                .sorted(Comparator.comparing(Appointment::getDate)
                        .thenComparing(Appointment::getTime))
                .collect(Collectors.toList());
    }

    //Devuelve una lista con los doctores que tengan el mismo código ? REVISAR
    public List<Appointment> findByDoctorCode(String doctorCode) {
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().getCode().equals(doctorCode))
                .collect(Collectors.toList());
    }

    //Devuelve una lista de citas que coincidan con la fecha que acepta como parámetro REVISAR
    public List<Appointment> findByDate(LocalDate date) {
        return appointments.stream()
                .filter(appointment -> appointment.getDate().equals(date))
                .collect(Collectors.toList());
    }

    //Devuelve una lista de citas filtrando por doctor y fecha/hora REVISAR
    public List<Appointment> findByDoctorAndDateRange(Doctor doctor, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().equals(doctor))
                .filter(appointment -> appointment.getDate().equals(date))
                .filter(appointment -> !appointment.getTime().isBefore(startTime) && !appointment.getTime().isAfter(endTime))
                .collect(Collectors.toList());
    }

    //Devuelve una lista de citas filtrando por paciente y fecha/hora REVISAR
    public List<Appointment> findByPatientAndDateRange(Patient patient, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return appointments.stream()
                .filter(appointment -> appointment.getPatient().equals(patient))
                .filter(appointment -> appointment.getDate().equals(date))
                .filter(appointment -> !appointment.getTime().isBefore(startTime) && !appointment.getTime().isAfter(endTime))
                .collect(Collectors.toList());
    }

    //Method para borrar citas
    public void delete(Appointment appointment) {
        appointments.remove(appointment);
    }
}

