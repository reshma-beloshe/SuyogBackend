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

import com.SuyogHospital.Master.OccupationMaster;
import com.SuyogHospital.Service.OccupationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Occupation")
public class OccupationController {

    @Autowired
    private OccupationService occupationService;

    @PostMapping("/addOccupation")
    public ResponseEntity<String> addOccupation(@RequestBody @Valid OccupationMaster occupationMaster) {
        Boolean isAdded = occupationService.addOccupation(occupationMaster);
        return isAdded ?
                new ResponseEntity<>("Occupation added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Occupation.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateOccupation/{occupationId}")
    public ResponseEntity<String> updateOccupation(
            @PathVariable("occupationId") @Positive Integer occupationId,
            @RequestBody @Valid OccupationMaster occupationMaster) {
        occupationMaster.setOccupationId(occupationId);
        Boolean isUpdated = occupationService.updateOccupation(occupationMaster);
        return isUpdated ?
                new ResponseEntity<>("Occupation updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Occupation.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteOccupation/{occupationId}")
    public ResponseEntity<String> deleteOccupation(@PathVariable("occupationId") @Positive Integer occupationId) {
        OccupationMaster occupationMaster = occupationService.getOccupation(occupationId);
        return occupationMaster != null && occupationService.deleteOccupation(occupationMaster) ?
                new ResponseEntity<>("Occupation deleted successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to delete Occupation.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getOccupation/{occupationId}")
    public ResponseEntity<OccupationMaster> getOccupation(@PathVariable("occupationId") @Positive Integer occupationId) {
        return new ResponseEntity<>(occupationService.getOccupation(occupationId), HttpStatus.OK);
    }

    @GetMapping("/getAllOccupations")
    public ResponseEntity<List<OccupationMaster>> getAllOccupations() {
        return new ResponseEntity<>(occupationService.getAllOccupations(), HttpStatus.OK);
    }
}
