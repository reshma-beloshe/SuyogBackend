package com.SuyogHospital.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.SuyogHospital.Master.EmployeeHistoryMaster;

public interface EmployeeHistoryDAO extends CrudRepository<EmployeeHistoryMaster, Integer> {
    List<EmployeeHistoryMaster> findByEmployeeId(int employeeId);
}
