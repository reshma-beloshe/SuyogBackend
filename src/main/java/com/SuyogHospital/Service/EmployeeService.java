package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.EmployeeMaster;

public interface EmployeeService {
    Boolean addEmployeeService(EmployeeMaster employeeMaster);
    Boolean updateEmployeeService(EmployeeMaster employeeMaster);
    Boolean deleteEmployeeService(EmployeeMaster employeeMaster);
    EmployeeMaster getEmployeeService(Integer employeeId);
    List<EmployeeMaster> getAllEmployeeService();
    
}
