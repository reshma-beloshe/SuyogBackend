package com.SuyogHospital.Controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SuyogHospital.Master.AdvanceAllocationMaster;
import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.ResponseDTO.AdvanceSummaryResponse;
import com.SuyogHospital.Service.AdvanceAllocationService;
import com.SuyogHospital.Service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/AdvanceAllocation")
public class AdvanceAllocationController {

    @Autowired
    private AdvanceAllocationService allocationService;

    @Autowired
    private EmployeeService employeeService;

    // ✅ ADD Advance Allocation
    @PostMapping("/add")
    public ResponseEntity<AdvanceSummaryResponse> addAdvanceAllocation(@RequestBody @Valid AdvanceAllocationMaster allocation) {
        Integer employeeId = Optional.ofNullable(allocation.getEmployee())
                .map(EmployeeMaster::getEmployeeId)
                .orElse(null);

        if (employeeId == null) {
            return ResponseEntity.badRequest().build();
        }

        EmployeeMaster employee = employeeService.getEmployeeService(employeeId);
        if (employee == null) {
            return ResponseEntity.badRequest().build();
        }

        allocation.setEmployee(employee);

        BigDecimal totalAdvance = allocation.calculateTotalAdvance();
        BigDecimal previousBalance = employee.getAdvanceAmount() != null ? employee.getAdvanceAmount() : BigDecimal.ZERO;
        BigDecimal recovery = allocation.getRecoveryFromSalary() != null ? allocation.getRecoveryFromSalary() : BigDecimal.ZERO;
        BigDecimal newBalance = previousBalance.add(totalAdvance).subtract(recovery);

        allocation.setBalance(newBalance);
        employee.setAdvanceAmount(newBalance);
        employeeService.updateEmployeeService(employee);

        allocationService.addAdvanceAllocationService(allocation);

        AdvanceSummaryResponse response = new AdvanceSummaryResponse(
                "Advance allocation added successfully!",
                allocation.getIpd(), allocation.getOpd(), allocation.getSalaryAdvanced(),
                allocation.getOthers(), totalAdvance, recovery, previousBalance, newBalance
        );

        return ResponseEntity.status(201).body(response);
    }

    // ✅ GET ALL
    @GetMapping("/get/{allocationId}")
    public ResponseEntity<AdvanceAllocationMaster> getAdvanceAllocation(
            @PathVariable("allocationId") Integer allocationId) {

        AdvanceAllocationMaster allocation = allocationService.getAdvanceAllocationService(allocationId);
        if (allocation == null) {
            return ResponseEntity.notFound().build();
        }

        // ✅ Compute previous balance (sum all allocations before this one)
        BigDecimal previousBalance = allocationService.calculatePreviousBalance(allocation.getEmployee().getEmployeeId(), allocation.getAllocationId());
        allocation.setPreviousBalance(previousBalance);

        return ResponseEntity.ok(allocation);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AdvanceAllocationMaster>> getAllAdvanceAllocations() {
        List<AdvanceAllocationMaster> allAllocations = allocationService.getAllAdvanceAllocationService();

        // ✅ Add previous balance for each record
        for (AdvanceAllocationMaster allocation : allAllocations) {
            BigDecimal previousBalance = allocationService.calculatePreviousBalance(allocation.getEmployee().getEmployeeId(), allocation.getAllocationId());
            allocation.setPreviousBalance(previousBalance);
        }

        return ResponseEntity.ok(allAllocations);
    }

    @GetMapping("/previousBalance/{employeeId}")
    public ResponseEntity<Map<String, Double>> getPreviousBalance(@PathVariable("employeeId") Long employeeId) {
        double previousBalance = allocationService.getPreviousBalanceByEmployee(employeeId);
        return ResponseEntity.ok(Collections.singletonMap("previousBalance", previousBalance));
    }


}
