package Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Entities.Patient;


public class PatientRepository {
    private List<Patient> patients = new ArrayList<>();

    public void save(Patient patient) {
        patients.add(patient);
    }

    public List<Patient> findAll() {
        return new ArrayList<>(patients);
    }

    public Optional<Patient> findByDUI(String dui) {
        return patients.stream()
                .filter(patient -> patient.getDUI().equals(dui))
                .findFirst();
    }
}

