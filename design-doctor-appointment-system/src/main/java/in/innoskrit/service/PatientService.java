package in.innoskrit.service;

import in.innoskrit.constant.Constant;
import in.innoskrit.exception.PatientAlreadyExistsException;
import in.innoskrit.exception.PatientNotFoundException;
import in.innoskrit.mode.Print;
import in.innoskrit.model.Patient;
import in.innoskrit.model.TimeSlot;
import in.innoskrit.repository.PatientRepository;

public class PatientService {
    private final PatientRepository patientRepository;
    private final Print print;

    public PatientService(PatientRepository patientRepository, Print print) {
        this.patientRepository = patientRepository;
        this.print = print;
    }

    public void registerPatient(Patient patient) {
        try {
            patientRepository.registerPatient(patient);
            print.printData(Constant.PATIENT_REGISTERED_SUCCESSFULLY);
        } catch(PatientAlreadyExistsException e) {
            print.printData(e.getMessage());
        }
    }

    public Patient getPatient(Integer patientId) {
        try {
            return patientRepository.getPatient(patientId);
        } catch (PatientNotFoundException e) {
            print.printData(Constant.PATIENT_NOT_FOUND);
        }
        return null;
    }

    public boolean isRegistered(Integer patientId) {
        return patientRepository.isRegistered(patientId);
    }

    public boolean isSlotFree(Integer patientId, TimeSlot slot) {
        try {
            return patientRepository.isSlotFree(patientId, slot);
        } catch (PatientNotFoundException e) {
            print.printData(e.getMessage());
        }
        return false;
    }

    public void releaseSlot(Integer patientId, TimeSlot slot) {
        patientRepository.releaseSlot(patientId, slot);
    }


}
