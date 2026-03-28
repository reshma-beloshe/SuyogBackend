package com.SuyogHospital.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Master.MawshiMaster;

public interface MawshiDAO extends JpaRepository<MawshiMaster, Integer>{

	  @Query("SELECT m FROM MawshiMaster m WHERE m.employee.employeeId = :employeeId AND FUNCTION('MONTH', m.dutyDate) = :month AND FUNCTION('YEAR', m.dutyDate) = :year")
	    List<MawshiMaster> findByEmployeeAndMonthAndYear(
	            @Param("employeeId") Integer employeeId,
	            @Param("month") int month,
	            @Param("year") int year);

	List<MawshiMaster> findByDate(LocalDate today);

	List<MawshiMaster> findByEmployee_EmployeeIdAndDateBetween(Integer employeeId, LocalDate start, LocalDate end);
	
	
	//Copy Date for the dutyInTime and dutyOutTime
	@Query("SELECT m FROM MawshiMaster m WHERE m.employee = :employee AND m.dutyDate = :date")
	MawshiMaster findByEmployeeAndDate(@Param("employee") EmployeeMaster employee, @Param("date") LocalDate date);

}
