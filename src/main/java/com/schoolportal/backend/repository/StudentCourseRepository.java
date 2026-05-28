package com.schoolportal.backend.repository;

import com.schoolportal.backend.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    // Inherits findAll(), save(), and count() out-of-the-box!
}