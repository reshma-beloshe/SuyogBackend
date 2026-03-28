package com.SuyogHospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SuyogHospital.Master.DesignationMaster;
import com.SuyogHospital.Service.DesignationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Designation")
public class DesignationController {

    @Autowired
    private DesignationService designationService;

    @PostMapping("/addDesignation")
    public ResponseEntity<String> addDesignation(@RequestBody @Valid DesignationMaster designationMaster) {
        Boolean isAdded = designationService.addDesignationService(designationMaster);
        return isAdded ? new ResponseEntity<>("Designation added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Designation.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateDesignation/{designationId}")
    public ResponseEntity<String> updateDesignation(
            @PathVariable("designationId") @Positive(message = "Designation ID must be positive") Integer designationId,
            @RequestBody @Valid DesignationMaster designationMaster) {
        designationMaster.setId(designationId);
        Boolean isUpdated = designationService.updateDesignationService(designationMaster);
        return isUpdated ? new ResponseEntity<>("Designation updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Designation.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteDesignation/{designationId}")
    public ResponseEntity<String> deleteDesignation(@PathVariable("designationId") @Positive Integer designationId) {
        DesignationMaster designationMaster = designationService.getDesignationService(designationId);
        if (designationMaster != null) {
            Boolean isDeleted = designationService.deleteDesignationService(designationMaster);
            return isDeleted ? new ResponseEntity<>("Designation deleted successfully!", HttpStatus.OK) :
                    new ResponseEntity<>("Failed to delete Designation.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Designation not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getDesignation/{designationId}")
    public ResponseEntity<DesignationMaster> getDesignation(@PathVariable("designationId") @Positive Integer designationId) {
        DesignationMaster designationMaster = designationService.getDesignationService(designationId);
        return designationMaster != null ? new ResponseEntity<>(designationMaster, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllDesignations")
    public ResponseEntity<List<DesignationMaster>> getAllDesignations() {
        List<DesignationMaster> allDesignations = designationService.getAllDesignationService();
        return new ResponseEntity<>(allDesignations, HttpStatus.OK);
    }
}
