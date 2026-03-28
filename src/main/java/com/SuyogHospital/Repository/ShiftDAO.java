package com.SuyogHospital.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SuyogHospital.Master.ShiftMaster;

@Repository
public interface ShiftDAO extends CrudRepository<ShiftMaster, Integer>{


//	ShiftMaster findByshiftName(String shiftName);

//	ShiftMaster findByShiftName(String shiftName);

	ShiftMaster findByShiftNameIgnoreCase(String shiftName);

	
}
