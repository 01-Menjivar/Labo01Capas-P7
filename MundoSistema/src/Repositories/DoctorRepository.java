package Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Entities.Doctor;


public class DoctorRepository {
    private List<Doctor> doctors = new ArrayList<>();

    public void save(Doctor doctor) {
        doctors.add(doctor);
    }

    public List<Doctor> findAll() {
        return new ArrayList<>(doctors);
    }

    public Optional<Doctor> findByCode(String code) {
        return doctors.stream()
                .filter(doctor -> doctor.getCode().equals(code))
                .findFirst();
    }

    public List<Doctor> findBySpecialty(String specialty) {
        return doctors.stream()
                .filter(doctor -> doctor.getSpecialty().equals(specialty))
                .collect(Collectors.toList());
    }
}

