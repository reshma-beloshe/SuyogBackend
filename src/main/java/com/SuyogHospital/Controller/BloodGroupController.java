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

import com.SuyogHospital.Master.BloodGroupMaster;
import com.SuyogHospital.Service.BloodGroupService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/BloodGroup")
public class BloodGroupController {

    @Autowired
    private BloodGroupService bloodGroupService;

    @PostMapping("/addBloodGroup")
    public ResponseEntity<String> addBloodGroup(@RequestBody @Valid BloodGroupMaster bloodGroupMaster) {
        Boolean isAdded = bloodGroupService.addBloodGroup(bloodGroupMaster);
        return isAdded ?
                new ResponseEntity<>("Blood Group added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Blood Group.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateBloodGroup/{bloodGroupId}")
    public ResponseEntity<String> updateBloodGroup(
            @PathVariable("bloodGroupId") @Positive Integer bloodGroupId,
            @RequestBody @Valid BloodGroupMaster bloodGroupMaster) {
        bloodGroupMaster.setBloodGroupId(bloodGroupId);
        Boolean isUpdated = bloodGroupService.updateBloodGroup(bloodGroupMaster);
        return isUpdated ?
                new ResponseEntity<>("Blood Group updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Blood Group.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteBloodGroup/{bloodGroupId}")
    public ResponseEntity<String> deleteBloodGroup(@PathVariable("bloodGroupId") @Positive Integer bloodGroupId) {
        BloodGroupMaster bloodGroupMaster = bloodGroupService.getBloodGroup(bloodGroupId);
        return bloodGroupMaster != null && bloodGroupService.deleteBloodGroup(bloodGroupMaster) ?
                new ResponseEntity<>("Blood Group deleted successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to delete Blood Group.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getBloodGroup/{bloodGroupId}")
    public ResponseEntity<BloodGroupMaster> getBloodGroup(@PathVariable("bloodGroupId") @Positive Integer bloodGroupId) {
        return new ResponseEntity<>(bloodGroupService.getBloodGroup(bloodGroupId), HttpStatus.OK);
    }

    @GetMapping("/getAllBloodGroups")
    public ResponseEntity<List<BloodGroupMaster>> getAllBloodGroups() {
        return new ResponseEntity<>(bloodGroupService.getAllBloodGroups(), HttpStatus.OK);
    }
}
