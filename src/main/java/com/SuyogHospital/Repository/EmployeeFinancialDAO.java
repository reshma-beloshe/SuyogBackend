package com.SuyogHospital.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.SuyogHospital.Master.EmployeeFinancialMaster;

public interface EmployeeFinancialDAO extends CrudRepository<EmployeeFinancialMaster, Integer>{

    List<EmployeeFinancialMaster> findByEmployeeEmployeeId(Integer employeeId);

    
    Optional<EmployeeFinancialMaster> findTopByEmployeeEmployeeIdOrderByEmpFinancialIdDesc(Integer employeeId);

}