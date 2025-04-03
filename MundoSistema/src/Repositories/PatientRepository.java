package Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Entities.Patient;

public class PatientRepository {
    //Lista mutable de pacientes
    private List<Patient> patients = new ArrayList<>();

    //Method que acepta un paciente y lo agrega mediante el méthod add
    public void save(Patient patient) {
        patients.add(patient);
    }

    //Retorna una lista de pacientes, concretamente una instancia de
    //ArrayList con los mismos elementos que patientes para no modificar
    //la instancia original
    public List<Patient> findAll() {
        return new ArrayList<>(patients);
    }

    //Retorna un paciente
    //Se crea un stream para poder operar sobre la lista de pacientes
    //usa el method filter para dejar solo aquellos con el mismo dui
    //toma el primero (aunque debe haber solo uno que coincida)
    public Optional<Patient> findByDUI(String dui) {
        return patients.stream()
                .filter(patient -> patient.getDUI().equals(dui))
                .findFirst();
    }
}

