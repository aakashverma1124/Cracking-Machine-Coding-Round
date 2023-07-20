package in.innoskrit;

import in.innoskrit.mode.ConsolePrint;
import in.innoskrit.mode.Print;
import in.innoskrit.model.Doctor;
import in.innoskrit.model.Patient;
import in.innoskrit.model.Specialization;
import in.innoskrit.model.TimeSlot;
import in.innoskrit.repository.BookingRepository;
import in.innoskrit.repository.DoctorRepository;
import in.innoskrit.repository.PatientRepository;
import in.innoskrit.service.BookingService;
import in.innoskrit.service.DoctorService;
import in.innoskrit.service.PatientService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        Print print = new ConsolePrint();

        DoctorService doctorService = new DoctorService(new DoctorRepository(), new ConsolePrint());
        PatientService patientService = new PatientService(new PatientRepository(), new ConsolePrint());
        BookingService bookingService = new BookingService(new BookingRepository(doctorService, patientService, new ConsolePrint()), new ConsolePrint());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        Doctor doctor1 = new Doctor(1, "Dr. Harry", new HashMap<>(), Specialization.DENTIST, 0.0);
        Doctor doctor2 = new Doctor(2, "Dr. Sam", new HashMap<>(), Specialization.ORTHOPEDIC, 0.0);
        Doctor doctor3 = new Doctor(3, "Dr. Christ", new HashMap<>(), Specialization.GENERAL_PHYSICIAN, 0.0);

        doctorService.registerDoctor(doctor1);
        doctorService.registerDoctor(doctor2);
        doctorService.registerDoctor(doctor3);

        doctorService.addAvailability(1, new TimeSlot(LocalDateTime.parse("21-07-2023 14:00", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 14:30", dateTimeFormatter)));
        doctorService.addAvailability(1, new TimeSlot(LocalDateTime.parse("21-07-2023 14:30", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 15:00", dateTimeFormatter)));
        doctorService.addAvailability(2, new TimeSlot(LocalDateTime.parse("20-07-2023 09:00", dateTimeFormatter),
                LocalDateTime.parse("20-07-2023 09:30", dateTimeFormatter)));
        doctorService.addAvailability(2, new TimeSlot(LocalDateTime.parse("21-07-2023 11:00", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 11:30", dateTimeFormatter)));
        doctorService.addAvailability(2, new TimeSlot(LocalDateTime.parse("21-07-2023 14:00", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 14:30", dateTimeFormatter)));
        doctorService.addAvailability(3, new TimeSlot(LocalDateTime.parse("21-07-2023 12:00", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 12:30", dateTimeFormatter)));
        doctorService.addAvailability(3, new TimeSlot(LocalDateTime.parse("21-07-2023 15:00", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 15:30", dateTimeFormatter)));

        Patient patient1 = new Patient(1, "Rock", new HashSet<>());
        Patient patient2 = new Patient(2, "Ram", new HashSet<>());
        Patient patient3 = new Patient(3, "Shreya", new HashSet<>());
        Patient patient4 = new Patient(4, "Ruby", new HashSet<>());
        Patient patient5 = new Patient(5, "Ravi", new HashSet<>());

        patientService.registerPatient(patient1);
        patientService.registerPatient(patient2);
        patientService.registerPatient(patient3);
        patientService.registerPatient(patient4);
        patientService.registerPatient(patient5);

        print.printData("----------------------------------");
        bookingService.bookAppointment(1, 1, new TimeSlot(LocalDateTime.parse("21-07-2023 14:00", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 14:30", dateTimeFormatter)));
        print.printData("----------------------------------");
        bookingService.bookAppointment(1, 2, new TimeSlot(LocalDateTime.parse("21-07-2023 14:00", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 14:30", dateTimeFormatter)));
        print.printData("----------------------------------");
        bookingService.bookAppointment(1, 1, new TimeSlot(LocalDateTime.parse("21-07-2023 14:30", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 15:00", dateTimeFormatter)));
        print.printData("----------------------------------");
        bookingService.bookAppointment(2, 1, new TimeSlot(LocalDateTime.parse("21-07-2023 14:30", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 15:00", dateTimeFormatter)));
        print.printData("----------------------------------");
        bookingService.bookAppointment(2, 2, new TimeSlot(LocalDateTime.parse("21-07-2023 11:00", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 11:30", dateTimeFormatter)));

        print.printData("----------------------------------");
        bookingService.cancelAppointment(4);

        print.printData("----------------------------------");
        bookingService.bookAppointment(3, 2, new TimeSlot(LocalDateTime.parse("21-07-2023 11:00", dateTimeFormatter),
                LocalDateTime.parse("21-07-2023 11:30", dateTimeFormatter)));

    }
}