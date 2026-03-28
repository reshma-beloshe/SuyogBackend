package com.SuyogHospital.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SuyogHospital.Master.PunchingRecordsMaster;

public interface PunchingRecordsDAO extends JpaRepository<PunchingRecordsMaster, Integer>{

	void flush();
	
	List<PunchingRecordsMaster> findByEmployee_EmployeeIdAndDateBetween(Integer employeeId, LocalDate startDate, LocalDate endDate);

	PunchingRecordsMaster findByEmployee_EmployeeIdAndDate(Integer employeeId, LocalDate date);

}
