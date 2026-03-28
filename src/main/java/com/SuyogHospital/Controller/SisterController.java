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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Master.ShiftMaster;
import com.SuyogHospital.Master.SisterMaster;
import com.SuyogHospital.ResponseDTO.SisterResponse;
import com.SuyogHospital.Service.EmployeeService;
import com.SuyogHospital.Service.ShiftService;
import com.SuyogHospital.Service.SisterService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Sister")
public class SisterController {

    @Autowired
    private SisterService sisterService;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addSister")
    public ResponseEntity<String> addSister(@RequestBody @Valid SisterMaster sister) {
        // Validate Shift
        String shiftValidationError = validateAndSetShift(sister);
        if (shiftValidationError != null) {
            return ResponseEntity.badRequest().body(shiftValidationError);
        }

        // Validate Employee
        String employeeValidationError = validateAndSetEmployee(sister);
        if (employeeValidationError != null) {
            return ResponseEntity.badRequest().body(employeeValidationError);
        }

        boolean isAdded = sisterService.addSister(sister);
        return isAdded
                ? ResponseEntity.status(HttpStatus.CREATED).body("Sister added successfully!")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Sister.");
    }

    @PutMapping("/updateSister/{sisterId}")
    public ResponseEntity<String> updateSister(
            @PathVariable("sisterId") @Positive(message = "Sister ID must be positive") Integer sisterId,
            @RequestBody @Valid SisterMaster sister) {

        sister.setSisterId(sisterId);

        // Validate Shift
        String shiftValidationError = validateAndSetShift(sister);
        if (shiftValidationError != null) {
            return ResponseEntity.badRequest().body(shiftValidationError);
        }

        // Validate Employee
        String employeeValidationError = validateAndSetEmployee(sister);
        if (employeeValidationError != null) {
            return ResponseEntity.badRequest().body(employeeValidationError);
        }

        boolean isUpdated = sisterService.updateSister(sister);
        return isUpdated
                ? ResponseEntity.ok("Sister updated successfully!")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Sister.");
    }

    @DeleteMapping("/deleteSister/{sisterId}")
    public ResponseEntity<String> deleteSister(@PathVariable("sisterId") @Positive Integer sisterId) {
        SisterMaster sister = sisterService.getSister(sisterId);
        if (sister == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sister not found.");
        }

        boolean isDeleted = sisterService.deleteSister(sister);
        return isDeleted
                ? ResponseEntity.ok("Sister deleted successfully!")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Sister.");
    }

    @GetMapping("/getSister/{sisterId}")
    public ResponseEntity<SisterMaster> getSister(@PathVariable("sisterId") @Positive Integer sisterId) {
        SisterMaster sister = sisterService.getSister(sisterId);
        return sister != null
                ? ResponseEntity.ok(sister)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/getAllSisters")
    public ResponseEntity<List<SisterMaster>> getAllSisters() {
        return ResponseEntity.ok(sisterService.getAllSisters());
    }

    // Validate and set ShiftMaster object
    private String validateAndSetShift(SisterMaster sister) {
        if (sister.getShift() == null || sister.getShift().getShiftId() == null) {
            return "Shift ID is required.";
        }

        ShiftMaster shift = shiftService.getShiftService(sister.getShift().getShiftId());
        if (shift == null) {
            return "Invalid Shift ID.";
        }

        sister.setShift(shift);
        return null;
    }

    // Validate and set EmployeeMaster object
    private String validateAndSetEmployee(SisterMaster sister) {
        if (sister.getEmployee() == null || sister.getEmployee().getEmployeeId() == null) {
            return "Employee ID is required.";
        }

        EmployeeMaster employee = employeeService.getEmployeeService(sister.getEmployee().getEmployeeId());
        if (employee == null) {
            return "Invalid Employee ID.";
        }

        sister.setEmployee(employee);
        return null;
    }
    
    
    //Records
    @GetMapping("/SisterRecordsAddUsingEmployeeIdAndMonth")
    public ResponseEntity<List<SisterResponse>> getSisterRecords(
            @RequestParam("employeeId") Integer employeeId,
            @RequestParam("month") String month) {

        List<SisterResponse> result = sisterService.getSisterRecords(employeeId, month);
        return ResponseEntity.ok(result);
    }
}
