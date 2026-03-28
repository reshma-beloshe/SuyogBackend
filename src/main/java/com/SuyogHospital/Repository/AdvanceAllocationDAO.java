package com.SuyogHospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SuyogHospital.Master.AdvanceAllocationMaster;

public interface AdvanceAllocationDAO extends JpaRepository<AdvanceAllocationMaster, Integer>{

	List<AdvanceAllocationMaster> findByEmployeeEmployeeIdOrderByAllocationIdAsc(Integer employeeId);

	@Query("SELECT a.balance FROM AdvanceAllocationMaster a WHERE a.employee.employeeId = :employeeId ORDER BY a.allocationId DESC")
    List<Double> findLatestBalanceByEmployee(@Param("employeeId") Integer employeeId);

}
