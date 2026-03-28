package com.SuyogHospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SuyogHospital.Master.ReceptionHistoryMaster;

public interface ReceptionHistoryDAO extends JpaRepository<ReceptionHistoryMaster, Integer>{

}
