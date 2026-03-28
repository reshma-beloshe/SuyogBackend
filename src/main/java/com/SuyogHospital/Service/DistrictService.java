package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.DistrictMaster;

public interface DistrictService {

    public Boolean addDistrictService(DistrictMaster districtMaster);
    public Boolean updateDistrictService(DistrictMaster districtMaster);
    public Boolean deleteDistrictService(DistrictMaster districtMaster);
    public DistrictMaster getDistrictService(Integer districtId);
    public List<DistrictMaster> getAllDistrictService();
}

