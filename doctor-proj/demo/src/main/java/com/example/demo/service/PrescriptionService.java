package com.example.demo.service;

import com.example.demo.entity.Prescription;
import com.example.demo.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id);
    }

    public List<Prescription> getPrescriptionsByPatientId(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }

    public List<Prescription> getPrescriptionsByDoctorId(Long doctorId) {
        return prescriptionRepository.findByDoctorId(doctorId);
    }

    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public Prescription updatePrescription(Long id, Prescription prescriptionDetails) {
        return prescriptionRepository.findById(id).map(prescription -> {
            prescription.setMedicineName(prescriptionDetails.getMedicineName());
            prescription.setDosage(prescriptionDetails.getDosage());
            prescription.setInstructions(prescriptionDetails.getInstructions());
            prescription.setPrescribedDate(prescriptionDetails.getPrescribedDate());
            prescription.setDoctor(prescriptionDetails.getDoctor());
            prescription.setPatient(prescriptionDetails.getPatient());
            return prescriptionRepository.save(prescription);
        }).orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
    }

    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
