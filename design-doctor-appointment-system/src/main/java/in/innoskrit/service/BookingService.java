package in.innoskrit.service;

import in.innoskrit.constant.Constant;
import in.innoskrit.exception.BookingNotFoundException;
import in.innoskrit.exception.DoctorNotFoundException;
import in.innoskrit.exception.PatientNotFoundException;
import in.innoskrit.exception.SlotAlreadyBookedException;
import in.innoskrit.mode.Print;
import in.innoskrit.model.*;
import in.innoskrit.repository.BookingRepository;
import in.innoskrit.repository.DoctorRepository;

import java.util.HashMap;
import java.util.Map;

public class BookingService {

    private final BookingRepository bookingRepository;
    private final Print print;
    public BookingService(BookingRepository bookingRepository, Print print) {
        this.bookingRepository = bookingRepository;
        this.print = print;
    }

    public Booking bookAppointment(Integer patientId, Integer doctorId, TimeSlot slot) {
        try {
            return bookingRepository.bookAppointment(patientId, doctorId, slot);
        } catch (Exception e) {
            print.printData(e.getMessage());
        }
        return null;
    }

    public void cancelAppointment(Integer bookingId) {
        try {
            bookingRepository.cancelAppointment(bookingId);
        } catch (Exception e) {
            print.printData(e.getMessage());
        }
    }

}
