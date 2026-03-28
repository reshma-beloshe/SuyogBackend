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

import com.SuyogHospital.Master.ReligionMaster;
import com.SuyogHospital.Service.ReligionService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Religion")
public class ReligionController {

    @Autowired
    private ReligionService religionService;

    @PostMapping("/addReligion")
    public ResponseEntity<String> addReligion(@RequestBody @Valid ReligionMaster religionMaster) {
        Boolean isAdded = religionService.addReligion(religionMaster);
        return isAdded ?
                new ResponseEntity<>("Religion added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Religion.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateReligion/{religionId}")
    public ResponseEntity<String> updateReligion(
            @PathVariable("religionId") @Positive Integer religionId,
            @RequestBody @Valid ReligionMaster religionMaster) {
        religionMaster.setReligionId(religionId);
        Boolean isUpdated = religionService.updateReligion(religionMaster);
        return isUpdated ?
                new ResponseEntity<>("Religion updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Religion.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteReligion/{religionId}")
    public ResponseEntity<String> deleteReligion(@PathVariable("religionId") @Positive Integer religionId) {
        ReligionMaster religionMaster = religionService.getReligion(religionId);
        return religionMaster != null && religionService.deleteReligion(religionMaster) ?
                new ResponseEntity<>("Religion deleted successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to delete Religion.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getReligion/{religionId}")
    public ResponseEntity<ReligionMaster> getReligion(@PathVariable("religionId") @Positive Integer religionId) {
        return new ResponseEntity<>(religionService.getReligion(religionId), HttpStatus.OK);
    }

    @GetMapping("/getAllReligions")
    public ResponseEntity<List<ReligionMaster>> getAllReligions() {
        return new ResponseEntity<>(religionService.getAllReligions(), HttpStatus.OK);
    }
}
