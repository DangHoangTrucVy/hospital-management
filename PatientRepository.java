package com.project.back_end.repositories;

import com.project.back_end.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Tìm bằng email
    Optional<Patient> findByEmail(String email);

    // Tìm bằng email hoặc số điện thoại
    Optional<Patient> findByEmailOrContactInfo(String email, String contactInfo);
}
