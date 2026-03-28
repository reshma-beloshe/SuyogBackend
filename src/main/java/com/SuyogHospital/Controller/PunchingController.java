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
import org.springframework.web.multipart.MultipartFile;

import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Master.PunchingMaster;
import com.SuyogHospital.Master.PunchingRecordsMaster;
import com.SuyogHospital.Master.ShiftMaster;
import com.SuyogHospital.Repository.PunchingRecordsDAO;
import com.SuyogHospital.ResponseDTO.PunchingRecordsAdjustmentRequest;
import com.SuyogHospital.Service.EmployeeService;
import com.SuyogHospital.Service.PunchingService;
import com.SuyogHospital.Service.ShiftService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Punching")
public class PunchingController {

    @Autowired
    private PunchingService punchingService;

    @Autowired
    private ShiftService shiftService; // Assuming you have a ShiftService to fetch ShiftMaster by ID

    @Autowired
    private EmployeeService employeeService; // Assuming you have an EmployeeService to fetch EmployeeMaster by ID
    
    @Autowired
    private PunchingRecordsDAO punchingRecordsDAO;

    @PostMapping("/addPunching")
    public ResponseEntity<String> addPunching(@RequestBody @Valid PunchingMaster punchingMaster) {
        ShiftMaster shift = shiftService.getShiftService(punchingMaster.getShift().getShiftId());
        EmployeeMaster employee = employeeService.getEmployeeService(punchingMaster.getEmployee().getEmployeeId());

        if (shift != null && employee != null) {
            punchingMaster.setShift(shift);
            punchingMaster.setEmployee(employee);
            Boolean isAdded = punchingService.addPunchingService(punchingMaster);
            if (isAdded) {
                return new ResponseEntity<>("Punching record added successfully!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to add punching record.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid Shift ID or Employee ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updatePunching/{punchingId}")
    public ResponseEntity<String> updatePunching(
            @PathVariable("punchingId") @Positive(message = "Punching ID must be positive") Integer punchingId,
            @RequestBody @Valid PunchingMaster punchingMaster) {
        ShiftMaster shift = shiftService.getShiftService(punchingMaster.getShift().getShiftId());
        EmployeeMaster employee = employeeService.getEmployeeService(punchingMaster.getEmployee().getEmployeeId());

        if (shift != null && employee != null) {
            punchingMaster.setShift(shift);
            punchingMaster.setEmployee(employee);
            punchingMaster.setPunchingId(punchingId);
            Boolean isUpdated = punchingService.updatePunchingService(punchingMaster);
            if (isUpdated) {
                return new ResponseEntity<>("Punching record updated successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update punching record.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid Shift ID or Employee ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletePunching/{punchingId}")
    public ResponseEntity<String> deletePunching(@PathVariable("punchingId") @Positive(message = "Punching ID must be positive") Integer punchingId) {
        PunchingMaster punchingMaster = punchingService.getPunchingService(punchingId);
        if (punchingMaster != null) {
            Boolean isDeleted = punchingService.deletePunchingService(punchingMaster);
            if (isDeleted) {
                return new ResponseEntity<>("Punching record deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete punching record.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getPunching/{punchingId}")
    public ResponseEntity<PunchingMaster> getPunching(@PathVariable("punchingId") @Positive(message = "Punching ID must be positive") Integer punchingId) {
        PunchingMaster punchingMaster = punchingService.getPunchingService(punchingId);
        if (punchingMaster != null) {
            return new ResponseEntity<>(punchingMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllPunchings")
    public ResponseEntity<List<PunchingMaster>> getAllPunchings() {
        List<PunchingMaster> allPunchings = punchingService.getAllPunchingService();
        return new ResponseEntity<>(allPunchings, HttpStatus.OK);
    }
    
    
    //Excel
    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            punchingService.processPunchingExcel(file);
            return ResponseEntity.ok("Punching data uploaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to process file: " + e.getMessage());
        }
    }
    
    
    @GetMapping("/getPunchingRecordsByEmployeeIdAndMonth/{employeeId}")
    public ResponseEntity<List<PunchingRecordsMaster>> getPunchingRecordsByEmployeeIdAndMonth(
            @PathVariable("employeeId") Integer employeeId,
            @RequestParam("month") String month) { // Format: YYYY-MM

        List<PunchingRecordsMaster> records = punchingService.getPunchingRecordsByEmployeeIdAndMonth(employeeId, month);
        return ResponseEntity.ok(records);
    }
    
    
    @PutMapping("/updateAdjustment/{punchingRecordId}")
    public ResponseEntity<String> updateAdjustment(
            @PathVariable("punchingRecordId") Integer punchingRecordId,
            @RequestBody PunchingRecordsAdjustmentRequest dto) {

        punchingService.updateAdjustment(punchingRecordId, dto);
        return ResponseEntity.ok("✅ Adjustment updated successfully");
    }


}