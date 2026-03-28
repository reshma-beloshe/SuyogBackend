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

import com.SuyogHospital.Master.SpecialisationMaster;
import com.SuyogHospital.Service.SpecialisationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Specialisation")
public class SpecialisationController {

    @Autowired
    private SpecialisationService specialisationService;

    @PostMapping("/addSpecialisation")
    public ResponseEntity<String> addSpecialisation(@RequestBody @Valid SpecialisationMaster specialisationMaster) {
        Boolean isAdded = specialisationService.addSpecialisationService(specialisationMaster);
        return isAdded ? new ResponseEntity<>("Specialisation added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Specialisation.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateSpecialisation/{specialisationId}")
    public ResponseEntity<String> updateSpecialisation(
            @PathVariable("specialisationId") @Positive(message = "Specialisation ID must be positive") Integer specialisationId,
            @RequestBody @Valid SpecialisationMaster specialisationMaster) {
        specialisationMaster.setSpecialisationId(specialisationId);
        Boolean isUpdated = specialisationService.updateSpecialisationService(specialisationMaster);
        return isUpdated ? new ResponseEntity<>("Specialisation updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Specialisation.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteSpecialisation/{specialisationId}")
    public ResponseEntity<String> deleteSpecialisation(@PathVariable("specialisationId") @Positive(message = "Specialisation ID must be positive") Integer specialisationId) {
        SpecialisationMaster specialisationMaster = specialisationService.getSpecialisationService(specialisationId);
        if (specialisationMaster != null) {
            Boolean isDeleted = specialisationService.deleteSpecialisationService(specialisationMaster);
            return isDeleted ? new ResponseEntity<>("Specialisation deleted successfully!", HttpStatus.OK) :
                    new ResponseEntity<>("Failed to delete Specialisation.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Specialisation not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getSpecialisation/{specialisationId}")
    public ResponseEntity<SpecialisationMaster> getSpecialisation(@PathVariable("specialisationId") @Positive Integer specialisationId) {
        SpecialisationMaster specialisationMaster = specialisationService.getSpecialisationService(specialisationId);
        return specialisationMaster != null ? new ResponseEntity<>(specialisationMaster, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllSpecialisations")
    public ResponseEntity<List<SpecialisationMaster>> getAllSpecialisations() {
        List<SpecialisationMaster> allSpecialisations = specialisationService.getAllSpecialisationService();
        return new ResponseEntity<>(allSpecialisations, HttpStatus.OK);
    }
}
