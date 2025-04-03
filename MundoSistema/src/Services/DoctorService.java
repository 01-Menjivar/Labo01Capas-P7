package Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import DTO.DoctorDTO;
import Entities.Doctor;
import Repositories.DoctorRepository;

public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    //Creación de un doctor
    public Doctor createDoctor(String name, String surname, String dui, LocalDate dateOfBirth, String specialty) {
        //Se genera el código
        String code = generateDoctorCode();
        //Se genera la fecha de reclutamiento en el momento que se crea
        LocalDate recruitmentDate = LocalDate.now();

        //Crea el nuevo objeto Doctor
        Doctor doctor = new Doctor(name, surname, dui, dateOfBirth, recruitmentDate.toString(), specialty, code);
        //Guarda en su repositorio correspondiente
        doctorRepository.save(doctor);

        //Devuelve el doctor creado
        return doctor;
    }

    //Funcion para generar los códigos
    public String generateDoctorCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder("ZNH-");

        code.append(random.nextInt(10))
                .append((char) ('A' + random.nextInt(26)))
                .append(random.nextInt(10));

        code.append("-MD-");

        code.append((char) ('A' + random.nextInt(26)))
                .append(random.nextInt(10));

        return code.toString();
    }

    //Funciones adicionales

    //Recuperar todos los doctores
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    //Recuperar los doctores por su código
    public Optional<Doctor> getDoctorByCode(String code) {
        return doctorRepository.findByCode(code);
    }

    //Recuperar una lista de doctores de una cierta especialidad
    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        return doctorRepository.findBySpecialty(specialty);
    }

    //Creación del DTO
    public DoctorDTO convertToDTO(Doctor doctor) {
        return new DoctorDTO(
                doctor.getName(),
                doctor.getSurname(),
                doctor.getDUI(),
                doctor.getDateOfBirth(),
                doctor.getRecruitmentDate(),
                doctor.getSpecialty(),
                doctor.getCode()
        );
    }
}

