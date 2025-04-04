package Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import DTO.AppointmentDTO;
import DTO.AppointmentDetailDTO;
import DTO.DoctorDTO;
import DTO.PatientDTO;
import Entities.Appointment;
import Entities.Doctor;
import Entities.Patient;
import Repositories.AppointmentRepository;

public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorService doctorService,
                              PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public Appointment createAppointment(Patient patient, Doctor doctor, LocalDate date, String specialty)
            throws IllegalStateException {
        LocalTime time;
        if (date.equals(LocalDate.now())) {
            //Si la cita es para el mismo día usa la hora actual
            time = LocalTime.now();
        } else {
            //De lo contrario genera un horario disponible para la cita
            time = generateAvailableTime(doctor, patient, date);
        }

        //Si no hay un cupo disponible lanza una excepción
        if (!isTimeSlotAvailable(doctor, patient, date, time)) {
            throw new IllegalStateException("El doctor o el paciente ya tiene una cita programada en este horario");
        }

        //Finalmente crea la cita
        Appointment appointment = new Appointment(patient, doctor, date, time, specialty, false);
        //Guarda en el repositorio y devuelve la cita
        appointmentRepository.save(appointment);
        return appointment;
    }

    private LocalTime generateAvailableTime(Doctor doctor, Patient patient, LocalDate date) {
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(16, 0);

        //Examina cupos de una hora, tomando candidatos iterando entre todas las horas de las 8 a las 16
        for (int hour = startTime.getHour(); hour < endTime.getHour(); hour++) {
            LocalTime candidateTime = LocalTime.of(hour, 0);
            //Si el cupón está disponible devuelve como horario al candidato
            if (isTimeSlotAvailable(doctor, patient, date, candidateTime)) {
                return candidateTime;
            }
        }

        //De lo contario muestra una excepción donde no hay horarios disponibles
        throw new IllegalStateException("No hay horarios disponibles para este día");
    }


    private boolean isTimeSlotAvailable(Doctor doctor, Patient patient, LocalDate date, LocalTime time) {
        //Toma las citas de un doctor en específico a una hora específica
        List<Appointment> doctorAppointments = appointmentRepository.findByDoctorAndDateRange(
                doctor, date, time, time.plusHours(1));

        //Si la lista NO vacía retorna falso
        if (!doctorAppointments.isEmpty()) {
            return false;
        }

        //Toma las citas de un paciente en específico a una hora específica
        List<Appointment> patientAppointments = appointmentRepository.findByPatientAndDateRange(
                patient, date, time, time.plusHours(1));

        //Retorna verdadero en caso que ni el doctor ni el paciente tengan citas
        return patientAppointments.isEmpty();
    }

    //Obtiene todas las citas ordenadas
    public List<Appointment> getAllAppointmentsSorted() {
        return appointmentRepository.findAllSorted();
    }

    //Obtiene todas las citas


    //Filtra citas por código del doctor
    public List<Appointment> getAppointmentsByDoctorCode(String doctorCode) {
        return appointmentRepository.findByDoctorCode(doctorCode);
    }

    //Elimina una cita
    public void cancelAppointment(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

    // Marca si el paciente asistió a la cita
    public void markAttendance(Appointment appointment, boolean attended) {
        appointment.setAttendance(attended);
    }

}
