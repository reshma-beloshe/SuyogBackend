package com.SuyogHospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SuyogHospital.Master.DepartmentMaster;
import com.SuyogHospital.Service.DepartmentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/addDepartment")
    public ResponseEntity<String> addDepartment(@RequestBody @Valid DepartmentMaster departmentMaster) {
        Boolean isAdded = departmentService.addDepartmentService(departmentMaster);
        if (isAdded) {
            return new ResponseEntity<>("Department added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add Department.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateDepartment/{departmentId}")
    public ResponseEntity<String> updateDepartment(
            @PathVariable("departmentId") @Positive(message = "Department ID must be positive") Integer departmentId,
            @RequestBody @Valid DepartmentMaster departmentMaster) {
        departmentMaster.setDepartmentId(departmentId);
        Boolean isUpdated = departmentService.updateDepartmentService(departmentMaster);
        if (isUpdated) {
            return new ResponseEntity<>("Department updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Department.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteDepartment/{departmentId}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("departmentId") @Positive(message = "Department ID must be positive") Integer departmentId) {
        DepartmentMaster departmentMaster = departmentService.getDepartmentService(departmentId);
        if (departmentMaster != null) {
            Boolean isDeleted = departmentService.deleteDepartmentService(departmentMaster);
            if (isDeleted) {
                return new ResponseEntity<>("Department deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete Department.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getDepartment/{departmentId}")
    public ResponseEntity<DepartmentMaster> getDepartment(@PathVariable("departmentId") @Positive(message = "Department ID must be positive") Integer departmentId) {
        DepartmentMaster departmentMaster = departmentService.getDepartmentService(departmentId);
        if (departmentMaster != null) {
            return new ResponseEntity<>(departmentMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<List<DepartmentMaster>> getAllDepartments() {
        List<DepartmentMaster> allDepartments = departmentService.getAllDepartmentService();
        return new ResponseEntity<>(allDepartments, HttpStatus.OK);
    }
}
