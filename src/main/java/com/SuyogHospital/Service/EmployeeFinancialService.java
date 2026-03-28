package com.SuyogHospital.Service;

import java.util.List;
import java.util.Optional;

import com.SuyogHospital.Master.EmployeeFinancialMaster;
import com.SuyogHospital.ResponseDTO.EmployeeFinancialWithPunchingResponse;

public interface EmployeeFinancialService {
		 Boolean addEmployeeFinancialService(EmployeeFinancialMaster employeeFinancialMaster);
		 Boolean updateEmployeeFinancialService(EmployeeFinancialMaster employeeFinancialMaster);
		 Boolean deleteEmployeeFinancialService(Integer employeeFinancialId); 
		 EmployeeFinancialMaster getEmployeeFinancialService(Integer employeeFinancialId);
		 List<EmployeeFinancialMaster> getAllEmployeeFinancialServices();


		 EmployeeFinancialWithPunchingResponse getAllFinancialDetailsWithPunchingByEmployeeId(
			        Integer employeeId, int month, int year);

		 List<EmployeeFinancialWithPunchingResponse> getAllFinancialDetailsWithPunchingByMonthAndYear(int month, int year);


		 Optional<EmployeeFinancialMaster> getLatestByEmployeeId(Integer employeeId);
		 EmployeeFinancialMaster saveOrUpdate(EmployeeFinancialMaster financial);

}
