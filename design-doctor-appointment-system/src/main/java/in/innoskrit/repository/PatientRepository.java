package in.innoskrit.repository;

import in.innoskrit.constant.Constant;
import in.innoskrit.exception.PatientAlreadyExistsException;
import in.innoskrit.exception.PatientNotFoundException;
import in.innoskrit.model.Patient;
import in.innoskrit.model.TimeSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PatientRepository {
    Map<Integer, Patient> patients = new HashMap<>();

    public void registerPatient(Patient patient) {
        if(patients.containsKey(patient.getId())) {
            throw new PatientAlreadyExistsException(Constant.PATIENT_ALREADY_EXISTS);
        }
        patients.put(patient.getId(), patient);
    }

    public Patient getPatient(Integer patientId) {
        if(!patients.containsKey(patientId)) {
            throw new PatientNotFoundException(Constant.PATIENT_NOT_FOUND);
        }
        return patients.get(patientId);
    }

    public boolean isRegistered(Integer patientId) {
        return patients.containsKey(patientId);
    }

    public boolean isSlotFree(Integer patientId, TimeSlot slot) {
        if(!patients.containsKey(patientId)) {
            throw new PatientNotFoundException(Constant.PATIENT_NOT_FOUND);
        }
        Patient patient = patients.get(patientId);
        Set<TimeSlot> bookedSlots = patient.getBookedSlots();

        return !bookedSlots.contains(slot);
    }

    public void releaseSlot(Integer patientId, TimeSlot slot) {
        if(!patients.containsKey(patientId)) {
            throw new PatientNotFoundException(Constant.PATIENT_NOT_FOUND);
        }
        Patient patient = patients.get(patientId);
        Set<TimeSlot> bookedSlots = patient.getBookedSlots();
        bookedSlots.remove(slot);
    }
}
