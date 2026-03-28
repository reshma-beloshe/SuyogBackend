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

import com.SuyogHospital.Master.DistrictMaster;
import com.SuyogHospital.Master.StateMaster;
import com.SuyogHospital.Service.DistrictService;
import com.SuyogHospital.Service.StateService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/District")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private StateService stateService;

    @PostMapping("/addDistrict")
    public ResponseEntity<String> addDistrict(@RequestBody @Valid DistrictMaster districtMaster) {
        StateMaster state = stateService.getStateService(districtMaster.getState().getStateId());
        if (state != null) {
            districtMaster.setState(state);
            Boolean isAdded = districtService.addDistrictService(districtMaster);
            if (isAdded) {
                return new ResponseEntity<>("District added successfully!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to add District.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid state ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateDistrict/{districtId}")
    public ResponseEntity<String> updateDistrict(
            @PathVariable("districtId") @Positive(message = "District ID must be positive") Integer districtId,
            @RequestBody @Valid DistrictMaster districtMaster) {
        StateMaster state = stateService.getStateService(districtMaster.getState().getStateId());
        if (state != null) {
            districtMaster.setState(state);
            districtMaster.setDistrictId(districtId);
            Boolean isUpdated = districtService.updateDistrictService(districtMaster);
            if (isUpdated) {
                return new ResponseEntity<>("District updated successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update District.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid state ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteDistrict/{districtId}")
    public ResponseEntity<String> deleteDistrict(@PathVariable("districtId") @Positive(message = "District ID must be positive") Integer districtId) {
        DistrictMaster districtMaster = districtService.getDistrictService(districtId);
        if (districtMaster != null) {
            Boolean isDeleted = districtService.deleteDistrictService(districtMaster);
            if (isDeleted) {
                return new ResponseEntity<>("District deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete District.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getDistrict/{districtId}")
    public ResponseEntity<DistrictMaster> getDistrict(@PathVariable("districtId") @Positive(message = "District ID must be positive") Integer districtId) {
        DistrictMaster districtMaster = districtService.getDistrictService(districtId);
        if (districtMaster != null) {
            return new ResponseEntity<>(districtMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllDistricts")
    public ResponseEntity<List<DistrictMaster>> getAllDistricts() {
        List<DistrictMaster> allDistricts = districtService.getAllDistrictService();
        return new ResponseEntity<>(allDistricts, HttpStatus.OK);
    }
}

