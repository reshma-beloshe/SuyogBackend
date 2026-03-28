package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.SisterMaster;
import com.SuyogHospital.ResponseDTO.SisterResponse;

public interface SisterService {
    Boolean addSister(SisterMaster sisterMaster);
    Boolean updateSister(SisterMaster sisterMaster);
    Boolean deleteSister(SisterMaster sisterMaster);
    SisterMaster getSister(Integer sisterId);
    List<SisterMaster> getAllSisters();
    
    List<SisterResponse> getSisterRecords(Integer employeeId, String month);

}
