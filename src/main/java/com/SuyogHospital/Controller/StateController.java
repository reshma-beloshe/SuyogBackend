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

import com.SuyogHospital.Master.CountryMaster;
import com.SuyogHospital.Master.StateMaster;
import com.SuyogHospital.Service.CountryService;
import com.SuyogHospital.Service.StateService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/State")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private CountryService countryService;

    @PostMapping("/addState")
    public ResponseEntity<String> addState(@RequestBody @Valid StateMaster stateMaster) {
        CountryMaster country = countryService.getCountryService(stateMaster.getCountry().getCountryId());
        if (country != null) {
            stateMaster.setCountry(country);
            Boolean isAdded = stateService.addStateService(stateMaster);
            if (isAdded) {
                return new ResponseEntity<>("State added successfully!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to add State.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid country ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateState/{stateId}")
    public ResponseEntity<String> updateState(
            @PathVariable("stateId") @Positive(message = "State ID must be positive") Integer stateId,
            @RequestBody @Valid StateMaster stateMaster) {
        CountryMaster country = countryService.getCountryService(stateMaster.getCountry().getCountryId());
        if (country != null) {
            stateMaster.setCountry(country);
            stateMaster.setStateId(stateId);
            Boolean isUpdated = stateService.updateStateService(stateMaster);
            if (isUpdated) {
                return new ResponseEntity<>("State updated successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update State.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid country ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteState/{stateId}")
    public ResponseEntity<String> deleteState(@PathVariable("stateId") @Positive(message = "State ID must be positive") Integer stateId) {
        StateMaster stateMaster = stateService.getStateService(stateId);
        if (stateMaster != null) {
            Boolean isDeleted = stateService.deleteStateService(stateMaster);
            if (isDeleted) {
                return new ResponseEntity<>("State deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete State.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getState/{stateId}")
    public ResponseEntity<StateMaster> getState(@PathVariable("stateId") @Positive(message = "State ID must be positive") Integer stateId) {
        StateMaster stateMaster = stateService.getStateService(stateId);
        if (stateMaster != null) {
            return new ResponseEntity<>(stateMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllStates")
    public ResponseEntity<List<StateMaster>> getAllStates() {
        List<StateMaster> allStates = stateService.getAllStateService();
        return new ResponseEntity<>(allStates, HttpStatus.OK);
    }
}
