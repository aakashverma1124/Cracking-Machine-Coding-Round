package in.innoskrit.repository;

import in.innoskrit.constant.Constant;
import in.innoskrit.exception.BookingNotFoundException;
import in.innoskrit.exception.DoctorNotFoundException;
import in.innoskrit.exception.PatientNotFoundException;
import in.innoskrit.exception.SlotAlreadyBookedException;
import in.innoskrit.mode.Print;
import in.innoskrit.model.*;
import in.innoskrit.service.DoctorService;
import in.innoskrit.service.PatientService;

import java.util.HashMap;
import java.util.Map;

public class BookingRepository {
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final Print print;

    private final Map<Integer, Booking> bookings = new HashMap<>();

    private Integer bookingId = 1;

    public BookingRepository(DoctorService doctorService, PatientService patientService, Print print) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.print = print;
    }

    public Booking bookAppointment(Integer patientId, Integer doctorId, TimeSlot slot) {
        if(!patientService.isRegistered(patientId)) throw new PatientNotFoundException(Constant.PATIENT_NOT_FOUND);
        if(!doctorService.isRegistered(doctorId)) throw new DoctorNotFoundException(Constant.DOCTOR_NOT_FOUND);

        Patient patient = patientService.getPatient(patientId);
        Doctor doctor = doctorService.getDoctor(doctorId);
        boolean isDoctorSlotFree = false;

        if(!patientService.isSlotFree(patientId, slot)) {
            throw new SlotAlreadyBookedException(Constant.PATIENT_SLOT_ALREADY_BOOKED);
        }

        if(doctorService.isSlotFree(doctorId, slot)) {
            isDoctorSlotFree = true;
        }

        Booking booking = null;
        if(isDoctorSlotFree) {
            Map<TimeSlot, SlotAvailability> slots = doctor.getSlots();
            slots.put(slot, SlotAvailability.BOOKED);
            booking = new Booking(bookingId++, doctor, patient, slot, false);
            print.printData(Constant.BOOKING_SUCCESSFUL);
        } else {
            booking = new Booking(bookingId++, doctor, patient, slot, true);
            print.printData(Constant.BOOKING_ADDED_TO_WAIT_LIST);
        }
        patient.getBookedSlots().add(slot);
        bookings.put(booking.getBookingId(), booking);
        print.printData("Booking Details:" +  booking.toString());
        return booking;
    }

    public void cancelAppointment(Integer bookingId) {
        if(!bookings.containsKey(bookingId)) {
            throw new BookingNotFoundException(Constant.BOOKING_NOT_FOUND);
        }
        Booking booking = bookings.get(bookingId);
        bookings.remove(bookingId);
        Doctor doctor = booking.getDoctor();
        Patient patient = booking.getPatient();
        doctorService.releaseSlot(doctor.getId(), booking.getSlot());
        patientService.releaseSlot(patient.getId(), booking.getSlot());
        print.printData(Constant.BOOKING_CANCELLED + " Booking Id: " + booking.getBookingId());
    }

}
