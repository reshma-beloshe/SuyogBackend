package com.SuyogHospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SuyogHospital.Master.EmployeeFinancialMaster;
import com.SuyogHospital.ResponseDTO.EmployeeFinancialWithPunchingResponse;
import com.SuyogHospital.Service.EmployeeFinancialService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
//@CrossOrigin(origins = "http://192.168.0.141:3000", allowCredentials = "true")
@RequestMapping("/EmployeeFinancial")
public class EmployeeFinancialController {

    @Autowired
    private EmployeeFinancialService employeeFinancialService;
    

    @PostMapping("/addEmployeeFinancial")
    public ResponseEntity<String> addEmployeeFinancial(@RequestBody @Valid EmployeeFinancialMaster employeeFinancialMaster) {
        Boolean isAdded = employeeFinancialService.addEmployeeFinancialService(employeeFinancialMaster);
        if (isAdded) {
            return new ResponseEntity<>("Employee Financial record added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add Employee Financial record.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateEmployeeFinancial/{empFinancialId}")
    public ResponseEntity<String> updateEmployeeFinancial(
            @PathVariable("empFinancialId") @Positive(message = "Employee Financial ID must be positive") Integer empFinancialId,
            @RequestBody @Valid EmployeeFinancialMaster employeeFinancialMaster) {
        employeeFinancialMaster.setEmpFinancialId(empFinancialId);
        Boolean isUpdated = employeeFinancialService.updateEmployeeFinancialService(employeeFinancialMaster);
        if (isUpdated) {
            return new ResponseEntity<>("Employee Financial record updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Employee Financial record.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteEmployeeFinancial/{empFinancialId}")
    public ResponseEntity<String> deleteEmployeeFinancial(@PathVariable("empFinancialId") @Positive(message = "Employee Financial ID must be positive") Integer empFinancialId) {
        // The service now directly takes the ID for deletion, no need to fetch the entity first
        Boolean isDeleted = employeeFinancialService.deleteEmployeeFinancialService(empFinancialId);
        if (isDeleted) {
            return new ResponseEntity<>("Employee Financial record deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete Employee Financial record. It might not exist.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmployeeFinancial/{empFinancialId}")
    public ResponseEntity<EmployeeFinancialMaster> getEmployeeFinancial(@PathVariable("empFinancialId") @Positive(message = "Employee Financial ID must be positive") Integer empFinancialId) {
        EmployeeFinancialMaster employeeFinancialMaster = employeeFinancialService.getEmployeeFinancialService(empFinancialId);
        if (employeeFinancialMaster != null) {
            return new ResponseEntity<>(employeeFinancialMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllEmployeeFinancials")
    public ResponseEntity<List<EmployeeFinancialMaster>> getAllEmployeeFinancials() {
        List<EmployeeFinancialMaster> allEmployeeFinancialRecords = employeeFinancialService.getAllEmployeeFinancialServices();
        return new ResponseEntity<>(allEmployeeFinancialRecords, HttpStatus.OK);
    }
    
    
    @GetMapping("/getAllFinancialDetailsWithPunchingByMonthAndYear")
    public ResponseEntity<List<EmployeeFinancialWithPunchingResponse>> getAllFinancialDetailsWithPunchingByMonthAndYear(
            @RequestParam("month") int month,
            @RequestParam("year") int year) {

        try {
            List<EmployeeFinancialWithPunchingResponse> responseList =
                employeeFinancialService.getAllFinancialDetailsWithPunchingByMonthAndYear(month, year);
            return ResponseEntity.ok(responseList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    
    
    @GetMapping("/getAllFinancialDetailsWithPunchingByEmployeeId")
    public ResponseEntity<EmployeeFinancialWithPunchingResponse> getAllFinancialDetailsWithPunching(
            @RequestParam("employeeId") Integer employeeId,
            @RequestParam("month") int month,
            @RequestParam("year") int year) {
        
        try {
            EmployeeFinancialWithPunchingResponse response = employeeFinancialService
                .getAllFinancialDetailsWithPunchingByEmployeeId(employeeId, month, year);
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}