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

import com.SuyogHospital.Master.NationalityMaster;
import com.SuyogHospital.Service.NationalityService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Nationality")
public class NationalityController {

    @Autowired
    private NationalityService nationalityService;

    @PostMapping("/addNationality")
    public ResponseEntity<String> addNationality(@RequestBody @Valid NationalityMaster nationalityMaster) {
        Boolean isAdded = nationalityService.addNationality(nationalityMaster);
        return isAdded ?
                new ResponseEntity<>("Nationality added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Nationality.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateNationality/{nationalityId}")
    public ResponseEntity<String> updateNationality(
            @PathVariable("nationalityId") @Positive Integer nationalityId,
            @RequestBody @Valid NationalityMaster nationalityMaster) {
        nationalityMaster.setNationalityId(nationalityId);
        Boolean isUpdated = nationalityService.updateNationality(nationalityMaster);
        return isUpdated ?
                new ResponseEntity<>("Nationality updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Nationality.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteNationality/{nationalityId}")
    public ResponseEntity<String> deleteNationality(@PathVariable("nationalityId") @Positive Integer nationalityId) {
        NationalityMaster nationalityMaster = nationalityService.getNationality(nationalityId);
        return nationalityMaster != null && nationalityService.deleteNationality(nationalityMaster) ?
                new ResponseEntity<>("Nationality deleted successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to delete Nationality.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getNationality/{nationalityId}")
    public ResponseEntity<NationalityMaster> getNationality(@PathVariable("nationalityId") @Positive Integer nationalityId) {
        return new ResponseEntity<>(nationalityService.getNationality(nationalityId), HttpStatus.OK);
    }

    @GetMapping("/getAllNationalities")
    public ResponseEntity<List<NationalityMaster>> getAllNationalities() {
        return new ResponseEntity<>(nationalityService.getAllNationalities(), HttpStatus.OK);
    }
}
