package com.SuyogHospital.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SuyogHospital.Master.EmployeeMaster;

@Repository
public interface EmployeeDAO extends CrudRepository<EmployeeMaster, Integer> {

    @Query("SELECT e FROM EmployeeMaster e " +
           "LEFT JOIN FETCH e.allowances " +
           "LEFT JOIN FETCH e.deductions " +
           "WHERE e.employeeId = :employeeId")
    Optional<EmployeeMaster> findByIdWithRelations(@Param("employeeId") Integer employeeId);

    EmployeeMaster findByEmployeeId(Integer employeeId);

    EmployeeMaster findByEmployeeFirstNameAndEmployeeLastName(String employeeFirstName, String employeeLastName);
    
    EmployeeMaster findByEmployeeCode(String employeeCode);

}
