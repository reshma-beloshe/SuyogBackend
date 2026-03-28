package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.NationalityMaster;

public interface NationalityService {
    Boolean addNationality(NationalityMaster nationalityMaster);
    Boolean updateNationality(NationalityMaster nationalityMaster);
    Boolean deleteNationality(NationalityMaster nationalityMaster);
    NationalityMaster getNationality(Integer nationalityId);
    List<NationalityMaster> getAllNationalities();
}
