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

import com.SuyogHospital.Master.DoctorTypeMaster;
import com.SuyogHospital.Service.DoctorTypeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/DoctorType")
public class DoctorTypeController {

    @Autowired
    private DoctorTypeService doctorTypeService;

    @PostMapping("/addDoctorType")
    public ResponseEntity<String> addDoctorType(@RequestBody @Valid DoctorTypeMaster doctorTypeMaster) {
        Boolean isAdded = doctorTypeService.addDoctorTypeService(doctorTypeMaster);
        if (isAdded) {
            return new ResponseEntity<>("Doctor Type added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add Doctor Type.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateDoctorType/{doctorTypeId}")
    public ResponseEntity<String> updateDoctorType(
            @PathVariable("doctorTypeId") @Positive(message = "Doctor Type ID must be positive") Integer doctorTypeId,
            @RequestBody @Valid DoctorTypeMaster doctorTypeMaster) {
        doctorTypeMaster.setDoctorTypeId(doctorTypeId);
        Boolean isUpdated = doctorTypeService.updateDoctorTypeService(doctorTypeMaster);
        if (isUpdated) {
            return new ResponseEntity<>("Doctor Type updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Doctor Type.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteDoctorType/{doctorTypeId}")
    public ResponseEntity<String> deleteDoctorType(@PathVariable("doctorTypeId") @Positive(message = "Doctor Type ID must be positive") Integer doctorTypeId) {
        DoctorTypeMaster doctorTypeMaster = doctorTypeService.getDoctorTypeService(doctorTypeId);
        if (doctorTypeMaster != null) {
            Boolean isDeleted = doctorTypeService.deleteDoctorTypeService(doctorTypeMaster);
            if (isDeleted) {
                return new ResponseEntity<>("Doctor Type deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete Doctor Type.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getDoctorType/{doctorTypeId}")
    public ResponseEntity<DoctorTypeMaster> getDoctorType(@PathVariable("doctorTypeId") @Positive(message = "Doctor Type ID must be positive") Integer doctorTypeId) {
        DoctorTypeMaster doctorTypeMaster = doctorTypeService.getDoctorTypeService(doctorTypeId);
        if (doctorTypeMaster != null) {
            return new ResponseEntity<>(doctorTypeMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllDoctorTypes")
    public ResponseEntity<List<DoctorTypeMaster>> getAllDoctorTypes() {
        List<DoctorTypeMaster> allDoctorTypes = doctorTypeService.getAllDoctorTypeService();
        return new ResponseEntity<>(allDoctorTypes, HttpStatus.OK);
    }
}
