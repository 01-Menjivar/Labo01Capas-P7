package Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Entities.Doctor;


public class DoctorRepository {
    //Lista de tipo Doctor
    private List<Doctor> doctors = new ArrayList<>();

    //Method para agregar doctores con .add
    public void save(Doctor doctor) {
        doctors.add(doctor);
    }

    //Method pata devolver a todos los doctores
    public List<Doctor> findAll() {
        return new ArrayList<>(doctors);
    }

    //method que devuelve un Doctor y hace uso de stream para usar
    // .filter para buscar coincidencias por código y toma el elemento
    //con .findFirst
    public Optional<Doctor> findByCode(String code) {
        return doctors.stream()
                .filter(doctor -> doctor.getCode().equals(code))
                .findFirst();
    }

    //Method que devuelve una lista de doctores e igualmente hace uso de streams
    //para poder usar .filter donde busca coincidencias por especialidad
    //luego recolecta todas las coincidencias y las hace una lista para poder devolverlo
    public List<Doctor> findBySpecialty(String specialty) {
        return doctors.stream()
                .filter(doctor -> doctor.getSpecialty().equals(specialty))
                .collect(Collectors.toList());
    }
}

