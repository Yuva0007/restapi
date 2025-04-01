package com.example.demo.service;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        return patientRepository.findById(id).map(patient -> {
            patient.setName(patientDetails.getName());
            patient.setAge(patientDetails.getAge());
            patient.setEmail(patientDetails.getEmail());
            patient.setPhoneNumber(patientDetails.getPhoneNumber());
            patient.setMedicalHistory(patientDetails.getMedicalHistory());
            return patientRepository.save(patient);
        }).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    // 🔹 Find all patients older than a certain age
    public List<Patient> getPatientsOlderThan(int age) {
        return patientRepository.findPatientsOlderThan(age);
    }

    // 🔹 Search patients by name (case-insensitive)
    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.searchPatientsByName(name);
    }

    // 🔹 Find a patient by email
    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    // 🔹 Get patients by phone number
    public List<Patient> getPatientsByPhoneNumber(String phoneNumber) {
        return patientRepository.findByPhoneNumber(phoneNumber);
    }

    // 🔹 Search patients by keyword in medical history
    public List<Patient> getPatientsByMedicalHistoryKeyword(String keyword) {
        return patientRepository.findByMedicalHistory(keyword);
    }
}
