package com.SuyogHospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SuyogHospital.Master.PatientCategoryMaster;
import com.SuyogHospital.Service.PatientCategoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/PatientCategory")
public class PatientCategoryController {

    @Autowired
    private PatientCategoryService patientCategoryService;

    @PostMapping("/addPatientCategory")
    public ResponseEntity<String> addPatientCategory(@RequestBody @Valid PatientCategoryMaster patientCategoryMaster) {
        Boolean isAdded = patientCategoryService.addPatientCategoryService(patientCategoryMaster);
        return isAdded ? new ResponseEntity<>("Patient Category added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Patient Category.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updatePatientCategory/{patientCategoryId}")
    public ResponseEntity<String> updatePatientCategory(
            @PathVariable("patientCategoryId") @Positive(message = "Patient Category ID must be positive") Integer patientCategoryId,
            @RequestBody @Valid PatientCategoryMaster patientCategoryMaster) {
        patientCategoryMaster.setPatientCategoryId(patientCategoryId);
        Boolean isUpdated = patientCategoryService.updatePatientCategoryService(patientCategoryMaster);
        return isUpdated ? new ResponseEntity<>("Patient Category updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Patient Category.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deletePatientCategory/{patientCategoryId}")
    public ResponseEntity<String> deletePatientCategory(@PathVariable("patientCategoryId") @Positive Integer patientCategoryId) {
        PatientCategoryMaster patientCategoryMaster = patientCategoryService.getPatientCategoryService(patientCategoryId);
        if (patientCategoryMaster != null) {
            Boolean isDeleted = patientCategoryService.deletePatientCategoryService(patientCategoryMaster);
            return isDeleted ? new ResponseEntity<>("Patient Category deleted successfully!", HttpStatus.OK) :
                    new ResponseEntity<>("Failed to delete Patient Category.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Patient Category not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getPatientCategory/{patientCategoryId}")
    public ResponseEntity<PatientCategoryMaster> getPatientCategory(@PathVariable("patientCategoryId") @Positive Integer patientCategoryId) {
        PatientCategoryMaster patientCategoryMaster = patientCategoryService.getPatientCategoryService(patientCategoryId);
        return patientCategoryMaster != null ? new ResponseEntity<>(patientCategoryMaster, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllPatientCategories")
    public ResponseEntity<List<PatientCategoryMaster>> getAllPatientCategories() {
        List<PatientCategoryMaster> allPatientCategories = patientCategoryService.getAllPatientCategoryService();
        return new ResponseEntity<>(allPatientCategories, HttpStatus.OK);
    }
}
