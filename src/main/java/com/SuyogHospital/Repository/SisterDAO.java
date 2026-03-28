package com.SuyogHospital.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Master.SisterMaster;

public interface SisterDAO extends JpaRepository<SisterMaster, Integer>{
	
	List<SisterMaster> findByEmployeeEmployeeIdAndDutyDateBetween(Integer employeeId, LocalDate startDate, LocalDate endDate);

    Optional<SisterMaster> findByEmployeeEmployeeIdAndDutyDate(Integer employeeId, LocalDate dutyDate);

	List<SisterMaster> findByDate(LocalDate today);

	List<SisterMaster> findByEmployee_EmployeeIdAndDateBetween(Integer employeeId, LocalDate start, LocalDate end);

	//Fill auto entry 
	@Query("SELECT s FROM SisterMaster s WHERE s.employee = :employee AND s.dutyDate = :date")
	SisterMaster findByEmployeeAndDate(@Param("employee") EmployeeMaster employee, @Param("date") LocalDate date);

}
