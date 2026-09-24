package com.edumerge.fee.repository;

import com.edumerge.fee.entity.FeeStructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeStructureRepository
        extends JpaRepository<FeeStructure, Long> {

    List<FeeStructure> findByCourse(String course);

    List<FeeStructure> findByAcademicYear(Integer academicYear);
}