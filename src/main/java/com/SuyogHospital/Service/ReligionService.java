package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.ReligionMaster;

public interface ReligionService {
    Boolean addReligion(ReligionMaster religionMaster);
    Boolean updateReligion(ReligionMaster religionMaster);
    Boolean deleteReligion(ReligionMaster religionMaster);
    ReligionMaster getReligion(Integer religionId);
    List<ReligionMaster> getAllReligions();
}
