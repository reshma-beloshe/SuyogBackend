package com.SuyogHospital.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SuyogHospital.Master.ReceptionMaster;

public interface ReceptionDAO extends JpaRepository<ReceptionMaster, Integer>{

	List<ReceptionMaster> findByMonthAndYear(int month, int year);

	List<ReceptionMaster> findAllByEmployeeEmployeeIdAndReceptionDate(Integer employeeId, LocalDate receptionDate);
}
