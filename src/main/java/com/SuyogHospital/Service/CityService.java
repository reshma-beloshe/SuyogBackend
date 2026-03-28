package com.SuyogHospital.Service;

import java.util.List;
import com.SuyogHospital.Master.CityMaster;

public interface CityService {

    public Boolean addCityService(CityMaster cityMaster);
    public Boolean updateCityService(CityMaster cityMaster);
    public Boolean deleteCityService(CityMaster cityMaster);
    public CityMaster getCityService(Integer cityId);
    public List<CityMaster> getAllCityService();
}
