package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.AreaMaster;

public interface AreaService {

    public Boolean addAreaService(AreaMaster areaMaster);
    public Boolean updateAreaService(AreaMaster areaMaster);
    public Boolean deleteAreaService(AreaMaster areaMaster);
    public AreaMaster getAreaService(Integer areaId);
    public List<AreaMaster> getAllAreaService();
}
