package com.example.demo.repository;

import com.example.demo.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find all appointments for a specific doctor
    @Query("SELECT a FROM Appointment a WHERE a.doctorName = :doctorName")
    List<Appointment> findByDoctor(@Param("doctorName") String doctorName);

    // Find all appointments for a specific patient
    @Query("SELECT a FROM Appointment a WHERE a.patientName = :patientName")
    List<Appointment> findByPatient(@Param("patientName") String patientName);

    // Find all appointments on a specific date
    @Query("SELECT a FROM Appointment a WHERE DATE(a.appointmentDate) = DATE(:date)")
    List<Appointment> findByAppointmentDate(@Param("date") LocalDateTime date);

    // Find upcoming appointments (after the current time)
    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate > :currentTime ORDER BY a.appointmentDate ASC")
    List<Appointment> findUpcomingAppointments(@Param("currentTime") LocalDateTime currentTime);

    // Count the number of appointments for a specific doctor
    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.doctorName = :doctorName")
    long countByDoctor(@Param("doctorName") String doctorName);
}
