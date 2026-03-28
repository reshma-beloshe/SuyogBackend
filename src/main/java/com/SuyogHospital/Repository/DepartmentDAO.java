package com.SuyogHospital.Repository;

import org.springframework.data.repository.CrudRepository;

import com.SuyogHospital.Master.DepartmentMaster;

public interface DepartmentDAO extends CrudRepository<DepartmentMaster, Integer>{

}
