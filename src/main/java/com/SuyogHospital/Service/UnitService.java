package com.SuyogHospital.Service;

import java.util.List;
import com.SuyogHospital.Master.UnitMaster;

public interface UnitService {

    public Boolean addUnitService(UnitMaster unitMaster);
    public Boolean updateUnitService(UnitMaster unitMaster);
    public Boolean deleteUnitService(UnitMaster unitMaster);
    public UnitMaster getUnitService(Integer unitId);
    public List<UnitMaster> getAllUnitService();
}
