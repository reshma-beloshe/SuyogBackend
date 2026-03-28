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
import org.springframework.web.bind.annotation.RestController;

import com.SuyogHospital.Master.AreaMaster;
import com.SuyogHospital.Master.BankMaster;
import com.SuyogHospital.Master.DepartmentMaster;
import com.SuyogHospital.Master.DesignationMaster;
import com.SuyogHospital.Master.EmpCategoryMaster;
import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Service.AreaService;
import com.SuyogHospital.Service.BankService;
import com.SuyogHospital.Service.DepartmentService;
import com.SuyogHospital.Service.DesignationService;
import com.SuyogHospital.Service.EmpCategoryService;
import com.SuyogHospital.Service.EmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DesignationService designationService;

    @Autowired
    private EmpCategoryService empCategoryService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private BankService bankService;

    /**
     * Add a new employee with validated related entities
     */
    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody @Valid EmployeeMaster employee) {
        String validationError = validateAndSetEmployeeRelations(employee);
        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        boolean isAdded = employeeService.addEmployeeService(employee);
        return isAdded
                ? ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully!")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Employee.");
    }

    /**
     * Update an existing employee
     */
    @PutMapping("/updateEmployee/{employeeId}")
    public ResponseEntity<String> updateEmployee(
            @PathVariable("employeeId") @Positive(message = "Employee ID must be positive") Integer employeeId,
            @RequestBody @Valid EmployeeMaster employee) {

        employee.setEmployeeId(employeeId);

        String validationError = validateAndSetEmployeeRelations(employee);
        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        boolean isUpdated = employeeService.updateEmployeeService(employee);
        return isUpdated
                ? ResponseEntity.ok("Employee updated successfully!")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Employee.");
    }

    /**
     * Delete employee by ID
     */
    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") @Positive Integer employeeId) {
        EmployeeMaster employee = employeeService.getEmployeeService(employeeId);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
        }

        boolean isDeleted = employeeService.deleteEmployeeService(employee);
        return isDeleted
                ? ResponseEntity.ok("Employee deleted successfully!")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Employee.");
    }

    /**
     * Get single employee by ID
     */
    @GetMapping("/getEmployee/{employeeId}")
    public ResponseEntity<EmployeeMaster> getEmployee(@PathVariable("employeeId") @Positive Integer employeeId) {
        EmployeeMaster employee = employeeService.getEmployeeService(employeeId);
        return employee != null
                ? ResponseEntity.ok(employee)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    /**
     * Get all employees
     */
    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<EmployeeMaster>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployeeService());
    }

    /**
     * Helper method: Validate and assign foreign key entities
     */
    private String validateAndSetEmployeeRelations(EmployeeMaster employee) {
        if (employee.getDepartment() == null || employee.getDepartment().getDepartmentId() == null) {
            return "Department ID is required.";
        }
        if (employee.getDesignation() == null || employee.getDesignation().getId() == null) {
            return "Designation ID is required.";
        }
        if (employee.getEmpCategory() == null || employee.getEmpCategory().getEmpCategoryId() == null) {
            return "EmpCategory ID is required.";
        }
        if (employee.getArea() == null || employee.getArea().getAreaId() == null) {
            return "Area ID is required.";
        }
        if (employee.getBank() == null || employee.getBank().getBankId() == null) {
            return "Bank ID is required.";
        }

        DepartmentMaster department = departmentService.getDepartmentService(employee.getDepartment().getDepartmentId());
        if (department == null) return "Invalid Department ID.";

        DesignationMaster designation = designationService.getDesignationService(employee.getDesignation().getId());
        if (designation == null) return "Invalid Designation ID.";

        EmpCategoryMaster empCategory = empCategoryService.getEmpCategory(employee.getEmpCategory().getEmpCategoryId());
        if (empCategory == null) return "Invalid EmpCategory ID.";

        AreaMaster area = areaService.getAreaService(employee.getArea().getAreaId());
        if (area == null) return "Invalid Area ID.";

        BankMaster bank = bankService.getBankService(employee.getBank().getBankId());
        if (bank == null) return "Invalid Bank ID.";

        employee.setDepartment(department);
        employee.setDesignation(designation);
        employee.setEmpCategory(empCategory);
        employee.setArea(area);
        employee.setBank(bank);

        return null;
    }
}
