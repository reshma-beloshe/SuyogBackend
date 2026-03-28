package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.AllowanceMaster;

public interface AllowanceService {

    public Boolean addAllowanceService(AllowanceMaster allowanceMaster);
    public Boolean updateAllowanceService(AllowanceMaster allowanceMaster);
    public Boolean deleteAllowanceService(AllowanceMaster allowanceMaster);
    public AllowanceMaster getAllowanceService(Integer allowanceId);
    public List<AllowanceMaster> getAllAllowanceService();
}
