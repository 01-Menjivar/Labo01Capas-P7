import Repositories.AppointmentRepository;
import Repositories.DoctorRepository;
import Repositories.PatientRepository;
import Services.AppointmentService;
import Services.DoctorService;
import Services.PatientService;
import UI.TerminalController;

public class Main {
    public static void main(String[] args) {

        // Inicializar repositorios
        DoctorRepository doctorRepository = new DoctorRepository();
        PatientRepository patientRepository = new PatientRepository();
        AppointmentRepository appointmentRepository = new AppointmentRepository();

        // Inicializar servicios
        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);
        AppointmentService appointmentService = new AppointmentService(
                appointmentRepository, doctorService, patientService);

        // Inicializar controlador de terminal
        TerminalController terminalController = new TerminalController(
                doctorService, patientService, appointmentService);

        // Iniciar la aplicación
        terminalController.start();
    }
}