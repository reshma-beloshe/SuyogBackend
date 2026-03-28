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
import com.SuyogHospital.Master.MawshiMaster;
import com.SuyogHospital.Master.ShiftMaster;
import com.SuyogHospital.ResponseDTO.MawshiResponse;
import com.SuyogHospital.Service.EmployeeService;
import com.SuyogHospital.Service.MawshiService;
import com.SuyogHospital.Service.ShiftService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Mawshi")
public class MawshiController {

    @Autowired
    private MawshiService mawshiService;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addMawshi")
    public ResponseEntity<String> addMawshi(@RequestBody @Valid MawshiMaster mawshi) {
        String validationError = validateAndSetShiftAndEmployee(mawshi);
        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        boolean isAdded = mawshiService.addMawshi(mawshi);
        return isAdded
                ? ResponseEntity.status(HttpStatus.CREATED).body("Mawshi added successfully!")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Mawshi.");
    }

    @PutMapping("/updateMawshi/{mawshiId}")
    public ResponseEntity<String> updateMawshi(
            @PathVariable("mawshiId") @Positive(message = "Mawshi ID must be positive") Integer mawshiId,
            @RequestBody @Valid MawshiMaster mawshi) {

        mawshi.setMawshiId(mawshiId);

        String validationError = validateAndSetShiftAndEmployee(mawshi);
        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        boolean isUpdated = mawshiService.updateMawshi(mawshi);
        return isUpdated
                ? ResponseEntity.ok("Mawshi updated successfully!")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Mawshi.");
    }

    @DeleteMapping("/deleteMawshi/{mawshiId}")
    public ResponseEntity<String> deleteMawshi(@PathVariable("mawshiId") @Positive Integer mawshiId) {
        MawshiMaster mawshi = mawshiService.getMawshi(mawshiId);
        if (mawshi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mawshi not found.");
        }

        boolean isDeleted = mawshiService.deleteMawshi(mawshi);
        return isDeleted
                ? ResponseEntity.ok("Mawshi deleted successfully!")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Mawshi.");
    }

    @GetMapping("/getMawshi/{mawshiId}")
    public ResponseEntity<MawshiMaster> getMawshi(@PathVariable("mawshiId") @Positive Integer mawshiId) {
        MawshiMaster mawshi = mawshiService.getMawshi(mawshiId);
        return mawshi != null
                ? ResponseEntity.ok(mawshi)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/getAllMawshis")
    public ResponseEntity<List<MawshiMaster>> getAllMawshis() {
        return ResponseEntity.ok(mawshiService.getAllMawshis());
    }

    private String validateAndSetShiftAndEmployee(MawshiMaster mawshi) {
        if (mawshi.getShift() == null || mawshi.getShift().getShiftId() == null) {
            return "Shift ID is required.";
        }

        ShiftMaster shift = shiftService.getShiftService(mawshi.getShift().getShiftId());
        if (shift == null) {
            return "Invalid Shift ID.";
        }
        mawshi.setShift(shift);

        if (mawshi.getEmployee() == null || mawshi.getEmployee().getEmployeeId() == null) {
            return "Employee ID is required.";
        }

        EmployeeMaster employee = employeeService.getEmployeeService(mawshi.getEmployee().getEmployeeId());
        if (employee == null) {
            return "Invalid Employee ID.";
        }
        mawshi.setEmployee(employee);

        return null;
    }
    
    //Records
    @GetMapping("/MawshiRecordsAddUsingEmployeeIdAndMonth")
    public ResponseEntity<List<MawshiResponse>> getMawshiRecords(
            @RequestParam("employeeId") Integer employeeId,
            @RequestParam("month") String month) {

        List<MawshiResponse> result = mawshiService.getMawshiRecords(employeeId, month);
        return ResponseEntity.ok(result);
    }

}
