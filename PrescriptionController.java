package com.example.clinic.controller;

import com.example.clinic.model.Prescription;
import com.example.clinic.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    /**
     * [EN] Get all prescriptions
     * [VN] Lấy danh sách tất cả toa thuốc
     */
    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    /**
     * [EN] Get prescription by ID
     * [VN] Lấy toa thuốc theo ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        return prescription != null ? ResponseEntity.ok(prescription) : ResponseEntity.notFound().build();
    }

    /**
     * [EN] Create new prescription with token authentication
     * [VN] Tạo mới toa thuốc với token xác thực
     */
    @PostMapping("/{token}")
    public ResponseEntity<Prescription> createPrescription(
            @PathVariable String token,
            @Valid @RequestBody Prescription prescription) {

        // TODO: validate token logic here
        if (!"VALID_TOKEN".equals(token)) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        Prescription created = prescriptionService.createPrescription(prescription);
        return ResponseEntity.ok(created);
    }

    /**
     * [EN] Update prescription by ID
     * [VN] Cập nhật toa thuốc theo ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(
            @PathVariable Long id,
            @Valid @RequestBody Prescription prescription) {

        Prescription updated = prescriptionService.updatePrescription(id, prescription);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    /**
     * [EN] Delete prescription by ID
     * [VN] Xoá toa thuốc theo ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        boolean deleted = prescriptionService.deletePrescription(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
