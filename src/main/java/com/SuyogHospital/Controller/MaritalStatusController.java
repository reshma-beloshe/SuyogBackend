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

import com.SuyogHospital.Master.MaritalStatusMaster;
import com.SuyogHospital.Service.MaritalStatusService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/MaritalStatus")
public class MaritalStatusController {

    @Autowired
    private MaritalStatusService maritalStatusService;

    @PostMapping("/addMaritalStatus")
    public ResponseEntity<String> addMaritalStatus(@RequestBody @Valid MaritalStatusMaster maritalStatusMaster) {
        Boolean isAdded = maritalStatusService.addMaritalStatus(maritalStatusMaster);
        return isAdded ?
                new ResponseEntity<>("Marital Status added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Marital Status.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateMaritalStatus/{maritalStatusId}")
    public ResponseEntity<String> updateMaritalStatus(
            @PathVariable("maritalStatusId") @Positive Integer maritalStatusId,
            @RequestBody @Valid MaritalStatusMaster maritalStatusMaster) {
        maritalStatusMaster.setMaritalStatusId(maritalStatusId);
        Boolean isUpdated = maritalStatusService.updateMaritalStatus(maritalStatusMaster);
        return isUpdated ?
                new ResponseEntity<>("Marital Status updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Marital Status.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteMaritalStatus/{maritalStatusId}")
    public ResponseEntity<String> deleteMaritalStatus(@PathVariable("maritalStatusId") @Positive Integer maritalStatusId) {
        MaritalStatusMaster maritalStatusMaster = maritalStatusService.getMaritalStatus(maritalStatusId);
        return maritalStatusMaster != null && maritalStatusService.deleteMaritalStatus(maritalStatusMaster) ?
                new ResponseEntity<>("Marital Status deleted successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to delete Marital Status.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getMaritalStatus/{maritalStatusId}")
    public ResponseEntity<MaritalStatusMaster> getMaritalStatus(@PathVariable("maritalStatusId") @Positive Integer maritalStatusId) {
        return new ResponseEntity<>(maritalStatusService.getMaritalStatus(maritalStatusId), HttpStatus.OK);
    }

    @GetMapping("/getAllMaritalStatuses")
    public ResponseEntity<List<MaritalStatusMaster>> getAllMaritalStatuses() {
        return new ResponseEntity<>(maritalStatusService.getAllMaritalStatuses(), HttpStatus.OK);
    }
}
