package com.SuyogHospital.Repository;

import org.springframework.data.repository.CrudRepository;

import com.SuyogHospital.Master.PatientCategoryMaster;

public interface PatientCategoryDAO extends CrudRepository<PatientCategoryMaster, Integer>{

}
