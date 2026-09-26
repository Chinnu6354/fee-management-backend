package com.edumerge.fee.controller;

import com.edumerge.fee.entity.FeeStructure;
import com.edumerge.fee.service.FeeStructureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/fee-structures")
public class FeeStructureController {

    private final FeeStructureService feeStructureService;

    public FeeStructureController(
            FeeStructureService feeStructureService) {
        this.feeStructureService = feeStructureService;
    }

    @PostMapping
    public ResponseEntity<FeeStructure> createFeeStructure(
            @RequestBody FeeStructure feeStructure) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(feeStructureService.createFeeStructure(feeStructure));
    }

    @GetMapping
    public ResponseEntity<List<FeeStructure>> getAllFeeStructures() {

        return ResponseEntity.ok(
                feeStructureService.getAllFeeStructures()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeeStructure> getFeeStructureById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                feeStructureService.getFeeStructureById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeStructure> updateFeeStructure(
            @PathVariable Long id,
            @RequestBody FeeStructure feeStructure) {

        return ResponseEntity.ok(
                feeStructureService.updateFeeStructure(
                        id,
                        feeStructure
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeeStructure(
            @PathVariable Long id) {

        feeStructureService.deleteFeeStructure(id);

        return ResponseEntity.ok(
                "Fee structure deleted successfully"
        );
    }

    @GetMapping("/course/{course}")
    public ResponseEntity<List<FeeStructure>> getByCourse(
            @PathVariable String course) {

        return ResponseEntity.ok(
                feeStructureService.getByCourse(course)
        );
    }

    @GetMapping("/academic-year/{academicYear}")
    public ResponseEntity<List<FeeStructure>> getByAcademicYear(
            @PathVariable Integer academicYear) {

        return ResponseEntity.ok(
                feeStructureService.getByAcademicYear(academicYear)
        );
    }
}