package in.innoskrit.service;

import in.innoskrit.constant.Constant;
import in.innoskrit.exception.DoctorAlreadyExistsException;
import in.innoskrit.exception.DoctorNotFoundException;
import in.innoskrit.exception.SpecializationNotFoundException;
import in.innoskrit.mode.Print;
import in.innoskrit.model.Doctor;
import in.innoskrit.model.Specialization;
import in.innoskrit.model.TimeSlot;
import in.innoskrit.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final Print print;

    public DoctorService(DoctorRepository doctorRepository, Print print) {
        this.doctorRepository = doctorRepository;
        this.print = print;
    }

    public void registerDoctor(Doctor doctor) {
        try {
            doctorRepository.registerDoctor(doctor);
            print.printData(Constant.DOCTOR_REGISTERED_SUCCESSFULLY);
        } catch (DoctorAlreadyExistsException e) {
            print.printData(e.getMessage());
        }
    }

    public Doctor getDoctor(Integer doctorId) {
        try {
            return doctorRepository.getDoctor(doctorId);
        } catch (DoctorNotFoundException e) {
            print.printData(e.getMessage());
        }
        return null;
    }

    public void addAvailability(Integer doctorId, TimeSlot slot) {
        try {
            doctorRepository.addAvailability(doctorId, slot);
            print.printData(Constant.SLOT_ADDED_SUCCESSFULLY);
        } catch (DoctorNotFoundException e) {
            print.printData(e.getMessage());
        }
    }

    public List<Doctor> getDoctorsBySpecialization(Specialization specialization) {
        try {
            return doctorRepository.getDoctorsBySpecialization(specialization);
        } catch(SpecializationNotFoundException e) {
            print.printData(e.getMessage());
        }
        return new ArrayList<>();
    }

    public boolean isRegistered(Integer doctorId) {
        return doctorRepository.isRegistered(doctorId);
    }

    public boolean isSlotFree(Integer doctorId, TimeSlot slot) {
        try {
            return doctorRepository.isSlotFree(doctorId, slot);
        } catch (Exception e) {
            print.printData(e.getMessage());
        }
        return false;
    }

    public void releaseSlot(Integer doctorId, TimeSlot slot) {
        doctorRepository.releaseSlot(doctorId, slot);
    }
}
