package UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import Entities.Appointment;
import Entities.Doctor;
import Entities.Patient;
import Services.AppointmentService;
import Services.DoctorService;
import Services.PatientService;

public class TerminalController {
    private final Scanner scanner;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;

    public TerminalController(DoctorService doctorService,
                              PatientService patientService,
                              AppointmentService appointmentService) {
        this.scanner = new Scanner(System.in);
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    //Logica que controla el menu
    public void start() {
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int option = readIntOption();

            switch (option) {
                case 1:
                    listAppointments();
                    break;
                case 2:
                    scheduleAppointment();
                    break;
                case 3:
                    addPatient();
                    break;
                case 4:
                    addDoctor();
                    break;
                case 5:
                    searchAppointmentsByDoctorCode();
                    break;
                case 6:
                    cancelAppointment();
                    break;
                case 7:
                    mundoSlavaVidas();
                    break;
                case 0:
                    exit = true;
                    System.out.println("¡Gracias por usar el sistema de citas médicas!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        scanner.close();
    }

    //Prints para mostrar el menu en la consola
    private void displayMenu() {
        System.out.println("\n===== HOSPITAL NACIONAL DE ZAUN - SISTEMA DE CITAS =====");
        System.out.println("1. Ver listado de citas");
        System.out.println("2. Agendar nueva cita");
        System.out.println("3. Agregar nuevo paciente");
        System.out.println("4. Agregar nuevo doctor");
        System.out.println("5. Buscar citas por código de doctor");
        System.out.println("6. Cancelar una cita");
        System.out.println("7. Mundo salva vidas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    //Funcion para leer los valores ingresados por el usuario
    private int readIntOption() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //Funcion para mostrar la lista de citas que se han agregado al sistema
    private void listAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointmentsSorted();

        //Mensaje por si no hay citas agendadas
        if (appointments.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }

        //Muestra el listado de citas
        System.out.println("\n===== LISTADO DE CITAS =====");
        System.out.printf("%-12s %-20s %-30s %-30s%n",
                "FECHA", "ESPECIALIDAD", "DOCTOR", "PACIENTE");
        System.out.println("--------------------------------------------------------------");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Appointment appointment : appointments) {
            System.out.printf("%-12s %-20s %-30s %-30s%n",
                    appointment.getDate().format(dateFormatter) + " " + appointment.getTime(),
                    appointment.getSpecialty(),
                    appointment.getDoctor().getName() + " " + appointment.getDoctor().getSurname(),
                    appointment.getPatient().getName() + " " + appointment.getPatient().getSurname());
        }
    }

    //Funcion para agregar una cita
    private void scheduleAppointment() {
        System.out.println("\n===== AGENDAR NUEVA CITA =====");

        // Seleccionar paciente
        Patient patient = selectPatient();
        if (patient == null) return;

        // Seleccionar especialidad
        System.out.print("Ingrese la especialidad requerida: ");
        String specialty = scanner.nextLine();

        // Mostrar y seleccionar doctor por especialidad
        List<Doctor> doctors = doctorService.getDoctorsBySpecialty(specialty);
        if (doctors.isEmpty()) {
            System.out.println("No hay doctores disponibles para esta especialidad.");
            return;
        }

        Doctor doctor = selectDoctor(doctors);
        if (doctor == null) return;

        // Fecha de la cita
        LocalDate appointmentDate = readDate("Ingrese la fecha de la cita (yyyy-MM-dd): ");
        if (appointmentDate == null) return;

        try {
            Appointment appointment = appointmentService.createAppointment(patient, doctor, appointmentDate, specialty);
            System.out.println("Cita agendada exitosamente para el " +
                    appointment.getDate() + " a las " + appointment.getTime());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Funcion auxiliar para elegir un paciente
    private Patient selectPatient() {
        List<Patient> patients = patientService.getAllPatients();

        //Mensaje por si no hay pacientes
        if (patients.isEmpty()) {
            System.out.println("No hay pacientes registrados. Por favor, registre un paciente primero.");
            return null;
        }

        //Muestra pacientes disponibles
        System.out.println("Pacientes disponibles:");
        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            System.out.printf("%d. %s %s (DUI: %s)%n",
                    i + 1, p.getName(), p.getSurname(), p.getDUI());
        }

        //Seleccion del paciente
        System.out.print("Seleccione un paciente (número): ");
        int selection = readIntOption();

        if (selection < 1 || selection > patients.size()) {
            System.out.println("Selección inválida.");
            return null;
        }

        return patients.get(selection - 1);
    }

    //Funcion auxiliar para elegir un doctor - Funciona igual que la de paciente
    private Doctor selectDoctor(List<Doctor> doctors) {
        System.out.println("Doctores disponibles:");
        for (int i = 0; i < doctors.size(); i++) {
            Doctor d = doctors.get(i);
            System.out.printf("%d. %s %s (Código: %s)%n",
                    i + 1, d.getName(), d.getSurname(), d.getCode());
        }

        System.out.print("Seleccione un doctor (número): ");
        int selection = readIntOption();

        if (selection < 1 || selection > doctors.size()) {
            System.out.println("Selección inválida.");
            return null;
        }

        return doctors.get(selection - 1);
    }

    //Funcion para agregar un paciente a la lista
    private void addPatient() {
        //Se registra los datos del paciente
        System.out.println("\n===== AGREGAR NUEVO PACIENTE =====");

        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        System.out.print("Apellido: ");
        String surname = scanner.nextLine();

        System.out.print("DUI: ");
        String dui = scanner.nextLine();

        LocalDate dateOfBirth = readDate("Fecha de nacimiento (yyyy-MM-dd): ");
        if (dateOfBirth == null) return;

        //Se agrega el paciente a la lista
        try {
            Patient patient = patientService.createPatient(name, surname, dui, dateOfBirth);
            System.out.println("Paciente agregado exitosamente!");
            if (patient.getDUI().equals("00000000-0")) {
                System.out.println("Nota: Se asignó el DUI predeterminado 00000000-0 por ser menor de edad.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Funcion para agregar un doctor a la lista
    private void addDoctor() {
        //Se registra los datos del doctor
        System.out.println("\n===== AGREGAR NUEVO DOCTOR =====");

        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        System.out.print("Apellido: ");
        String surname = scanner.nextLine();

        System.out.print("DUI: ");
        String dui = scanner.nextLine();

        LocalDate dateOfBirth = readDate("Fecha de nacimiento (yyyy-MM-dd): ");
        if (dateOfBirth == null) return;

        System.out.print("Especialidad: ");
        String specialty = scanner.nextLine();

        //Se agrega el doctor a la lista
        try {
            Doctor doctor = doctorService.createDoctor(name, surname, dui, dateOfBirth, specialty);
            System.out.println("Doctor agregado exitosamente!");
            System.out.println("Código asignado: " + doctor.getCode());
            System.out.println("Fecha de reclutamiento: " + doctor.getRecruitmentDate());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Funcion para buscar citas por codigo de doctor
    private void searchAppointmentsByDoctorCode() {
        System.out.println("\n===== BUSCAR CITAS POR CÓDIGO DE DOCTOR =====");

        //Se ingresa el codigo
        System.out.print("Ingrese el código del doctor: ");
        String code = scanner.nextLine();

        //Se busca y se muestra todas las coincidencias
        Optional<Doctor> doctorOpt = doctorService.getDoctorByCode(code);
        if (!doctorOpt.isPresent()) {
            System.out.println("No se encontró ningún doctor con ese código.");
            return;
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorCode(code);

        if (appointments.isEmpty()) {
            System.out.println("El doctor no tiene citas programadas.");
            return;
        }

        Doctor doctor = doctorOpt.get();
        System.out.println("\nCitas del Dr. " + doctor.getName() + " " + doctor.getSurname() + ":");
        System.out.printf("%-15s %-20s %-30s %-10s%n",
                "FECHA", "HORA", "PACIENTE", "ASISTENCIA");
        System.out.println("------------------------------------------------------------");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Appointment appointment : appointments) {
            System.out.printf("%-15s %-20s %-30s %-10s%n",
                    appointment.getDate().format(dateFormatter),
                    appointment.getTime(),
                    appointment.getPatient().getName() + " " + appointment.getPatient().getSurname(),
                    appointment.isAttendance() ? "Sí" : "No");
        }
    }

    //Funcion para cancelar una cita
    private void cancelAppointment() {
        //Se busca si hay citas, si hay se muestran
        System.out.println("\n===== CANCELAR CITA =====");

        List<Appointment> appointments = appointmentService.getAllAppointmentsSorted();

        if (appointments.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }

        System.out.println("Citas disponibles:");
        for (int i = 0; i < appointments.size(); i++) {
            Appointment a = appointments.get(i);
            System.out.printf("%d. %s %s - Dr. %s %s - %s %s%n",
                    i + 1,
                    a.getPatient().getName(),
                    a.getPatient().getSurname(),
                    a.getDoctor().getName(),
                    a.getDoctor().getSurname(),
                    a.getDate(),
                    a.getTime());
        }

        //Se elige la cita a cancelar
        System.out.print("Seleccione la cita a cancelar (número): ");
        int selection = readIntOption();

        if (selection < 1 || selection > appointments.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Appointment appointmentToCancel = appointments.get(selection - 1);
        appointmentService.cancelAppointment(appointmentToCancel);
        System.out.println("Cita cancelada exitosamente.");
    }

    //El boton de Dr. Mundo que no hace nada XD
    private void mundoSlavaVidas() {
        System.out.println("Dr. Mundo es el mejor Top laner");
    }

    //Funcion auxiliar para leer y verificar la fecha
    private LocalDate readDate(String prompt) {
        System.out.print(prompt);
        String dateStr = scanner.nextLine();

        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Use yyyy-MM-dd");
            return null;
        }
    }
}

