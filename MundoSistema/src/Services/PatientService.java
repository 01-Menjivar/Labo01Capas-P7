package Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import DTO.PatientDTO;
import Entities.Patient;
import Repositories.PatientRepository;

public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    //Creación del paciente
    public Patient createPatient(String name, String surname, String dui, LocalDate dateOfBirth) {
        Patient patient = new Patient(name, surname, dui, dateOfBirth);
        //Guarda en el repositorio
        patientRepository.save(patient);
        //Retorna el paciente
        return patient;
    }

    //Methods adicionales
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientByDUI(String dui) {
        return patientRepository.findByDUI(dui);
    }

    public PatientDTO convertToDTO(Patient patient) {
        return new PatientDTO(
                patient.getName(),
                patient.getSurname(),
                patient.getDUI(),
                patient.getDateOfBirth()
        );
    }
}
