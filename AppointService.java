package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private List<Appointment> appointmentList = new ArrayList<>();

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentList;
    }

    // Get appointment by ID
    public Appointment getAppointmentById(int id) {
        return appointmentList.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Get appointments by patient ID
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return appointmentList.stream()
                .filter(a -> a.getPatientId() == patientId)
                .collect(Collectors.toList());
    }

    // Get appointments by doctor ID
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        return appointmentList.stream()
                .filter(a -> a.getDoctorId() == doctorId)
                .collect(Collectors.toList());
    }

    // Add new appointment
    public Appointment addAppointment(Appointment appointment) {
        appointmentList.add(appointment);
        return appointment;
    }

    // Update an appointment
    public Appointment updateAppointment(int id, Appointment updatedAppointment) {
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getId() == id) {
                appointmentList.set(i, updatedAppointment);
                return updatedAppointment;
            }
        }
        return null;
    }

    // Delete an appointment
    public boolean deleteAppointment(int id) {
        return appointmentList.removeIf(a -> a.getId() == id);
    }
}
