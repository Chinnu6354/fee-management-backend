package com.edumerge.fee.repository;

import com.edumerge.fee.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    boolean existsByStudentId(String studentId);
}