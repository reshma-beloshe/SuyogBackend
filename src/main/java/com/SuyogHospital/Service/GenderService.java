package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.GenderMaster;

public interface GenderService {
    Boolean addGender(GenderMaster genderMaster);
    Boolean updateGender(GenderMaster genderMaster);
    Boolean deleteGender(GenderMaster genderMaster);
    GenderMaster getGender(Integer genderId);
    List<GenderMaster> getAllGenders();
}
