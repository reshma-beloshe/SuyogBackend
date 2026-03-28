package com.SuyogHospital.Repository;

import org.springframework.data.repository.CrudRepository;

import com.SuyogHospital.Master.PunchingHistoryMaster;

public interface PunchingHistoryDAO extends CrudRepository<PunchingHistoryMaster, Integer>{

}
