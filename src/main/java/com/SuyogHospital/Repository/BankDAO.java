package com.SuyogHospital.Repository;

import org.springframework.data.repository.CrudRepository;

import com.SuyogHospital.Master.BankMaster;

public interface BankDAO extends CrudRepository<BankMaster, Integer>{

}
