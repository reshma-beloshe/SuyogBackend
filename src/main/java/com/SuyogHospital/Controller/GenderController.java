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

import com.SuyogHospital.Master.GenderMaster;
import com.SuyogHospital.Service.GenderService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Gender")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @PostMapping("/addGender")
    public ResponseEntity<String> addGender(@RequestBody @Valid GenderMaster genderMaster) {
        Boolean isAdded = genderService.addGender(genderMaster);
        return isAdded ?
                new ResponseEntity<>("Gender added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Gender.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateGender/{genderId}")
    public ResponseEntity<String> updateGender(
            @PathVariable("genderId") @Positive Integer genderId,
            @RequestBody @Valid GenderMaster genderMaster) {
        genderMaster.setGenderId(genderId);
        Boolean isUpdated = genderService.updateGender(genderMaster);
        return isUpdated ?
                new ResponseEntity<>("Gender updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Gender.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteGender/{genderId}")
    public ResponseEntity<String> deleteGender(@PathVariable("genderId") @Positive Integer genderId) {
        GenderMaster genderMaster = genderService.getGender(genderId);
        return genderMaster != null && genderService.deleteGender(genderMaster) ?
                new ResponseEntity<>("Gender deleted successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to delete Gender.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getGender/{genderId}")
    public ResponseEntity<GenderMaster> getGender(@PathVariable("genderId") @Positive Integer genderId) {
        return new ResponseEntity<>(genderService.getGender(genderId), HttpStatus.OK);
    }

    @GetMapping("/getAllGenders")
    public ResponseEntity<List<GenderMaster>> getAllGenders() {
        return new ResponseEntity<>(genderService.getAllGenders(), HttpStatus.OK);
    }
}
