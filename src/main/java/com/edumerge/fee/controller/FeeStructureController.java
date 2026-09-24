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
    public ResponseEntity<FeeStructure> create(
            @RequestBody FeeStructure feeStructure) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        feeStructureService
                                .createFeeStructure(feeStructure)
                );
    }

    @GetMapping
    public ResponseEntity<List<FeeStructure>> getAll() {

        return ResponseEntity.ok(
                feeStructureService
                        .getAllFeeStructures()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeeStructure> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                feeStructureService
                        .getFeeStructureById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeStructure> update(
            @PathVariable Long id,
            @RequestBody FeeStructure feeStructure) {

        return ResponseEntity.ok(
                feeStructureService
                        .updateFeeStructure(
                                id,
                                feeStructure
                        )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        feeStructureService
                .deleteFeeStructure(id);

        return ResponseEntity.ok(
                "Fee structure deleted successfully"
        );
    }

    @GetMapping("/course/{course}")
    public ResponseEntity<List<FeeStructure>> byCourse(
            @PathVariable String course) {

        return ResponseEntity.ok(
                feeStructureService
                        .getByCourse(course)
        );
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<FeeStructure>> byYear(
            @PathVariable Integer year) {

        return ResponseEntity.ok(
                feeStructureService
                        .getByAcademicYear(year)
        );
    }
}