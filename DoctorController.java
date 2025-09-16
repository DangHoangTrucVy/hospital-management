package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    // Demo in-memory list for now (replace with database later)
    private List<Doctor> doctorList = new ArrayList<>();

    // Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorList;
    }

    // Get a doctor by ID
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable int id) {
        return doctorList.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Add a new doctor
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        doctorList.add(doctor);
        return doctor;
    }

    // Update a doctor
    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable int id, @RequestBody Doctor updatedDoctor) {
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorList.get(i).getId() == id) {
                doctorList.set(i, updatedDoctor);
                return updatedDoctor;
            }
        }
        return null;
    }

    // Delete a doctor
    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable int id) {
        boolean removed = doctorList.removeIf(d -> d.getId() == id);
        return removed ? "Doctor deleted successfully" : "Doctor not found";
    }
}
