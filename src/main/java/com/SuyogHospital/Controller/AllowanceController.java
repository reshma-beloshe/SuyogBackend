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

import com.SuyogHospital.Master.AllowanceMaster;
import com.SuyogHospital.Service.AllowanceService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Allowance")
public class AllowanceController {

    @Autowired
    private AllowanceService allowanceService;

    @PostMapping("/addAllowance")
    public ResponseEntity<String> addAllowance(@RequestBody @Valid AllowanceMaster allowanceMaster) {
        Boolean isAdded = allowanceService.addAllowanceService(allowanceMaster);
        if (isAdded) {
            return new ResponseEntity<>("Allowance added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add Allowance.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateAllowance/{allowanceId}")
    public ResponseEntity<String> updateAllowance(
            @PathVariable("allowanceId") @Positive(message = "Allowance ID must be positive") Integer allowanceId,
            @RequestBody @Valid AllowanceMaster allowanceMaster) {
        allowanceMaster.setAllowanceId(allowanceId);
        Boolean isUpdated = allowanceService.updateAllowanceService(allowanceMaster);
        if (isUpdated) {
            return new ResponseEntity<>("Allowance updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Allowance.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAllowance/{allowanceId}")
    public ResponseEntity<String> deleteAllowance(@PathVariable("allowanceId") @Positive(message = "Allowance ID must be positive") Integer allowanceId) {
        AllowanceMaster allowanceMaster = allowanceService.getAllowanceService(allowanceId);
        if (allowanceMaster != null) {
            Boolean isDeleted = allowanceService.deleteAllowanceService(allowanceMaster);
            if (isDeleted) {
                return new ResponseEntity<>("Allowance deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete Allowance.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllowance/{allowanceId}")
    public ResponseEntity<AllowanceMaster> getAllowance(@PathVariable("allowanceId") @Positive(message = "Allowance ID must be positive") Integer allowanceId) {
        AllowanceMaster allowanceMaster = allowanceService.getAllowanceService(allowanceId);
        if (allowanceMaster != null) {
            return new ResponseEntity<>(allowanceMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllAllowances")
    public ResponseEntity<List<AllowanceMaster>> getAllAllowances() {
        List<AllowanceMaster> allAllowances = allowanceService.getAllAllowanceService();
        return new ResponseEntity<>(allAllowances, HttpStatus.OK);
    }
}
