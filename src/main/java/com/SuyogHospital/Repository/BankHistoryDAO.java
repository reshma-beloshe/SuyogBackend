package com.SuyogHospital.Repository;

import org.springframework.data.repository.CrudRepository;

import com.SuyogHospital.Master.BankHistoryMaster;

public interface BankHistoryDAO extends CrudRepository<BankHistoryMaster, Integer>{

}
