package com.edumerge.fee.service;

import com.edumerge.fee.entity.FeeStructure;
import com.edumerge.fee.repository.FeeStructureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeStructureService {

    private final FeeStructureRepository feeStructureRepository;

    public FeeStructureService(
            FeeStructureRepository feeStructureRepository) {

        this.feeStructureRepository = feeStructureRepository;
    }

    public FeeStructure createFeeStructure(
            FeeStructure feeStructure) {

        return feeStructureRepository.save(feeStructure);
    }

    public List<FeeStructure> getAllFeeStructures() {

        return feeStructureRepository.findAll();
    }

    public FeeStructure getFeeStructureById(Long id) {

        return feeStructureRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Fee structure not found"
                        ));
    }

    public FeeStructure updateFeeStructure(
            Long id,
            FeeStructure updatedFeeStructure) {

        FeeStructure feeStructure =
                getFeeStructureById(id);

        feeStructure.setFeeHead(
                updatedFeeStructure.getFeeHead()
        );

        feeStructure.setCourse(
                updatedFeeStructure.getCourse()
        );

        feeStructure.setDepartment(
                updatedFeeStructure.getDepartment()
        );

        feeStructure.setAcademicYear(
                updatedFeeStructure.getAcademicYear()
        );

        feeStructure.setAmount(
                updatedFeeStructure.getAmount()
        );

        feeStructure.setActive(
                updatedFeeStructure.getActive()
        );

        return feeStructureRepository.save(feeStructure);
    }

    public void deleteFeeStructure(Long id) {

        FeeStructure feeStructure =
                getFeeStructureById(id);

        feeStructureRepository.delete(feeStructure);
    }

    public List<FeeStructure> getByCourse(String course) {

        return feeStructureRepository.findByCourse(course);
    }

    public List<FeeStructure> getByAcademicYear(
            Integer academicYear) {

        return feeStructureRepository
                .findByAcademicYear(academicYear);
    }
}