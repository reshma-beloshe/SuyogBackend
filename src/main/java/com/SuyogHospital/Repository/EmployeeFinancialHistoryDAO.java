package com.SuyogHospital.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.SuyogHospital.Master.EmployeeFinancialHistoryMaster;
import com.SuyogHospital.Master.EmployeeFinancialMaster;

public interface EmployeeFinancialHistoryDAO extends CrudRepository<EmployeeFinancialHistoryMaster, Integer>{


}
