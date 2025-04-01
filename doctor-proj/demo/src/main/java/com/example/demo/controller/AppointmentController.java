package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments/get")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/appointments/get/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/appointments/add")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @PutMapping("/appointments/put/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentDetails);
            return ResponseEntity.ok(updatedAppointment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/appointments/del/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    // Custom JPA Endpoints

    @GetMapping("/appointments/doctor/{doctorName}")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable String doctorName) {
        return appointmentService.getAppointmentsByDoctor(doctorName);
    }

    @GetMapping("/appointments/patient/{patientName}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable String patientName) {
        return appointmentService.getAppointmentsByPatient(patientName);
    }

    @GetMapping("/appointments/date/{date}")
    public List<Appointment> getAppointmentsByDate(@PathVariable String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        return appointmentService.getAppointmentsByDate(dateTime);
    }

    @GetMapping("/appointments/upcoming")
    public List<Appointment> getUpcomingAppointments() {
        return appointmentService.getUpcomingAppointments();
    }

    @GetMapping("/appointments/count/{doctorName}")
    public long countAppointmentsByDoctor(@PathVariable String doctorName) {
        return appointmentService.countAppointmentsByDoctor(doctorName);
    }
}
