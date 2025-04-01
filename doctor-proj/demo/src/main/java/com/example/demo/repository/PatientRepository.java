package com.example.demo.repository;

import com.example.demo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Find all patients older than a certain age
    @Query("SELECT p FROM Patient p WHERE p.age > :age")
    List<Patient> findPatientsOlderThan(@Param("age") int age);

    //  Find patients by name (case-insensitive search)
    @Query("SELECT p FROM Patient p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Patient> searchPatientsByName(@Param("name") String name);

    //  Find patient by email (unique constraint)
    @Query("SELECT p FROM Patient p WHERE p.email = :email")
    Patient findByEmail(@Param("email") String email);

    //  Find patients with specific phone number
    @Query("SELECT p FROM Patient p WHERE p.phoneNumber = :phone")
    List<Patient> findByPhoneNumber(@Param("phone") String phoneNumber);

    //  Get all patients with a specific medical history keyword
    @Query("SELECT p FROM Patient p WHERE LOWER(p.medicalHistory) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Patient> findByMedicalHistory(@Param("keyword") String keyword);
}
