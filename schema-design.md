# Database Schema Design

## Tables

### Patient
- patient_id INT PRIMARY KEY AUTO_INCREMENT
- name VARCHAR(100)
- date_of_birth DATE
- gender ENUM('Male', 'Female')
- contact_info VARCHAR(100)

### Doctor
- doctor_id INT PRIMARY KEY AUTO_INCREMENT
- name VARCHAR(100)
- specialty VARCHAR(50)
- contact_info VARCHAR(100)

### Appointment
- appointment_id INT PRIMARY KEY AUTO_INCREMENT
- patient_id INT
- doctor_id INT
- appointment_date DATE
- appointment_time TIME
- FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)
- FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id)

### Admin
- admin_id INT PRIMARY KEY AUTO_INCREMENT
- username VARCHAR(50)
- password VARCHAR(100)
