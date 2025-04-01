package com.example.demo.controller;

import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients/get")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/get/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/patients/add")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping("/patients/put/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        try {
            Patient updatedPatient = patientService.updatePatient(id, patientDetails);
            return ResponseEntity.ok(updatedPatient);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/patients/del/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Get patients older than a certain age
    @GetMapping("/patients/age/{age}")
    public List<Patient> getPatientsOlderThan(@PathVariable int age) {
        return patientService.getPatientsOlderThan(age);
    }

    // 🔹 Search patients by name (case-insensitive)
    @GetMapping("/patients/search/{name}")
    public List<Patient> searchPatientsByName(@PathVariable String name) {
        return patientService.searchPatientsByName(name);
    }

    // 🔹 Find a patient by email
    @GetMapping("/patients/email/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable String email) {
        Patient patient = patientService.getPatientByEmail(email);
        return patient != null ? ResponseEntity.ok(patient) : ResponseEntity.notFound().build();
    }

    // 🔹 Get patients by phone number
    @GetMapping("/patients/phone/{phoneNumber}")
    public List<Patient> getPatientsByPhoneNumber(@PathVariable String phoneNumber) {
        return patientService.getPatientsByPhoneNumber(phoneNumber);
    }

    // 🔹 Search patients by keyword in medical history
    @GetMapping("/patients/history/{keyword}")
    public List<Patient> getPatientsByMedicalHistoryKeyword(@PathVariable String keyword) {
        return patientService.getPatientsByMedicalHistoryKeyword(keyword);
    }
}
