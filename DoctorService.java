package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.models.Doctor;
import com.project.back_end.repositories.AppointmentRepository;
import com.project.back_end.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Lấy danh sách tất cả bác sĩ
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Lấy bác sĩ theo ID
    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElse(null);
    }

    // Tạo hoặc cập nhật bác sĩ
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Xóa bác sĩ
    public void deleteDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    // Lấy thời gian khám khả dụng của bác sĩ theo ngày
    public List<LocalTime> getDoctorAvailability(Long doctorId, LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date);

        List<LocalTime> allSlots = new ArrayList<>();
        for (int hour = 9; hour < 17; hour++) {
            allSlots.add(LocalTime.of(hour, 0));
            allSlots.add(LocalTime.of(hour, 30));
        }

        appointments.forEach(a -> allSlots.remove(a.getAppointmentTime()));

        return allSlots;
    }

    // Xác thực đăng nhập bác sĩ
    public boolean validateDoctorLogin(String email, String password) {
        Doctor doctor = doctorRepository.findByEmail(email);
        if (doctor != null) {
            return passwordEncoder.matches(password, doctor.getPassword());
        }
        return false;
    }
}
