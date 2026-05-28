package com.schoolportal.backend.repository;

import com.schoolportal.backend.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentEmail(String studentEmail);
}