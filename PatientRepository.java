
package com.project.back_end.repositories;

import com.project.back_end.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    // JpaRepository đã cung cấp các phương thức CRUD cơ bản
    // Bạn có thể thêm các truy vấn tùy chỉnh nếu cần, ví dụ:

    // Optional<Patient> findByEmail(String email);
}
