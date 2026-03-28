package com.SuyogHospital.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SuyogHospital.Master.PunchingMaster;
import com.SuyogHospital.Master.PunchingRecordsMaster;

@Repository
public interface PunchingDAO extends CrudRepository<PunchingMaster, Integer>{

	 List<PunchingMaster> findByEmployeeEmployeeIdAndPunchingDateBetween(
		        Integer employeeId, LocalDate startDate, LocalDate endDate);

	 @Query("SELECT p FROM PunchingMaster p WHERE p.employee.employeeId = :employeeId AND FUNCTION('MONTH', p.punchingDate) = :month AND FUNCTION('YEAR', p.punchingDate) = :year")
	    List<PunchingMaster> findByEmployeeAndMonthAndYear(
	            @Param("employeeId") Integer employeeId,
	            @Param("month") int month,
	            @Param("year") int year);

	void flush();

	List<PunchingMaster> findByEmployee_EmployeeIdAndDateBetween(Integer employeeId, LocalDate startDate, LocalDate endDate);
	
    PunchingMaster findByEmployee_EmployeeIdAndDate(Integer employeeId, LocalDate date);

//	void setShift(ShiftMaster shiftName);


}
