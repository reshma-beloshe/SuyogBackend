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

import com.SuyogHospital.Master.DeductionMaster;
import com.SuyogHospital.Service.DeductionService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Deduction")
public class DeductionController {

    @Autowired
    private DeductionService deductionService;

    @PostMapping("/addDeduction")
    public ResponseEntity<String> addDeduction(@RequestBody @Valid DeductionMaster deductionMaster) {
        Boolean isAdded = deductionService.addDeductionService(deductionMaster);
        return isAdded 
            ? new ResponseEntity<>("Deduction added successfully!", HttpStatus.CREATED)
            : new ResponseEntity<>("Failed to add Deduction.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateDeduction/{deductionId}")
    public ResponseEntity<String> updateDeduction(
            @PathVariable("deductionId") @Positive(message = "Deduction ID must be positive") Integer deductionId,
            @RequestBody @Valid DeductionMaster deductionMaster) {

        deductionMaster.setDeductionId(deductionId);
        Boolean isUpdated = deductionService.updateDeductionService(deductionMaster);
        return isUpdated 
            ? new ResponseEntity<>("Deduction updated successfully!", HttpStatus.OK)
            : new ResponseEntity<>("Failed to update Deduction.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteDeduction/{deductionId}")
    public ResponseEntity<String> deleteDeduction(@PathVariable("deductionId") @Positive Integer deductionId) {
        DeductionMaster deductionMaster = deductionService.getDeductionService(deductionId);
        if (deductionMaster != null) {
            Boolean isDeleted = deductionService.deleteDeductionService(deductionMaster);
            return isDeleted 
                ? new ResponseEntity<>("Deduction deleted successfully!", HttpStatus.OK)
                : new ResponseEntity<>("Failed to delete Deduction.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Deduction not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getDeduction/{deductionId}")
    public ResponseEntity<DeductionMaster> getDeduction(@PathVariable("deductionId") @Positive Integer deductionId) {
        DeductionMaster deductionMaster = deductionService.getDeductionService(deductionId);
        return deductionMaster != null 
            ? new ResponseEntity<>(deductionMaster, HttpStatus.OK)
            : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllDeductions")
    public ResponseEntity<List<DeductionMaster>> getAllDeductions() {
        List<DeductionMaster> allDeductions = deductionService.getAllDeductionService();
        return new ResponseEntity<>(allDeductions, HttpStatus.OK);
    }
}
