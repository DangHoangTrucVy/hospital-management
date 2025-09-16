package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.models.Appointment;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;

    // --- Basic CRUD (example) ---
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }

    // --- New endpoint: Get doctor availability ---
    @GetMapping("/{id}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestParam String role,
            @RequestParam String date // YYYY-MM-DD
    ) {
        if (!tokenService.validateToken(token, role)) {
            return ResponseEntity.status(403).body("Invalid token or insufficient permissions");
        }

        LocalDate appointmentDate = LocalDate.parse(date);
        List<Appointment> availableAppointments = doctorService.getAppointmentsByDoctorAndDate(id, appointmentDate);
        return ResponseEntity.ok(availableAppointments);
    }
}
