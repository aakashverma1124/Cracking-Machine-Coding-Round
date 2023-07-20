package in.innoskrit.repository;

import in.innoskrit.constant.Constant;
import in.innoskrit.exception.DoctorAlreadyExistsException;
import in.innoskrit.exception.DoctorNotFoundException;
import in.innoskrit.exception.SlotNotFoundException;
import in.innoskrit.exception.SpecializationNotFoundException;
import in.innoskrit.model.Doctor;
import in.innoskrit.model.SlotAvailability;
import in.innoskrit.model.Specialization;
import in.innoskrit.model.TimeSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRepository {
    Map<Integer, Doctor> doctors = new HashMap<>();
    Map<Specialization, List<Doctor>> doctorsWithSpecialization = new HashMap<>();

    public void registerDoctor(Doctor doctor) {
        if(doctors.containsKey(doctor.getId())) {
            throw new DoctorAlreadyExistsException(Constant.DOCTOR_ALREADY_EXISTS);
        }
        doctors.put(doctor.getId(), doctor);
        if(!doctorsWithSpecialization.containsKey(doctor.getSpecialization())) {
            doctorsWithSpecialization.put(doctor.getSpecialization(), new ArrayList<>());
        }
        doctorsWithSpecialization.get(doctor.getSpecialization()).add(doctor);
    }

    public Doctor getDoctor(Integer doctorId) {
        if(!doctors.containsKey(doctorId)) {
            throw new DoctorNotFoundException(Constant.DOCTOR_NOT_FOUND);
        }
        return doctors.get(doctorId);
    }

    public void addAvailability(Integer doctorId, TimeSlot slot) {
        if(!doctors.containsKey(doctorId)) {
            throw new DoctorNotFoundException(Constant.DOCTOR_NOT_FOUND);
        }
        Doctor doctor = doctors.get(doctorId);
        Map<TimeSlot, SlotAvailability> slots = doctor.getSlots();
        slots.put(slot, SlotAvailability.FREE);
    }

    public List<Doctor> getDoctorsBySpecialization(Specialization specialization) {
        if(!doctorsWithSpecialization.containsKey(specialization)) {
            throw new SpecializationNotFoundException(Constant.DOCTOR_NOT_FOUND_WITH_SPECIALIZATION);
        }
        return doctorsWithSpecialization.get(specialization);
    }

    public boolean isRegistered(Integer doctorId) {
        return doctors.containsKey(doctorId);
    }

    public boolean isSlotFree(Integer doctorId, TimeSlot slot) {
        if(!doctors.containsKey(doctorId)) {
            throw new DoctorNotFoundException(Constant.DOCTOR_NOT_FOUND);
        }
        Doctor doctor = doctors.get(doctorId);
        Map<TimeSlot, SlotAvailability> slots = doctor.getSlots();
        if(!slots.containsKey(slot)) {
            throw new SlotNotFoundException(Constant.SLOT_NOT_FOUND);
        }
        return slots.get(slot).equals(SlotAvailability.FREE);
    }

    public void releaseSlot(Integer doctorId, TimeSlot slot) {
        if(!doctors.containsKey(doctorId)) {
            throw new DoctorNotFoundException(Constant.DOCTOR_NOT_FOUND);
        }
        Doctor doctor = doctors.get(doctorId);
        Map<TimeSlot, SlotAvailability> slots = doctor.getSlots();
        if(!slots.containsKey(slot)) {
            throw new SlotNotFoundException(Constant.SLOT_NOT_FOUND);
        }
        slots.put(slot, SlotAvailability.FREE);
    }
}
