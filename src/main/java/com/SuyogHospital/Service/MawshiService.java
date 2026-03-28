package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.MawshiMaster;
import com.SuyogHospital.ResponseDTO.MawshiResponse;

public interface MawshiService {
    Boolean addMawshi(MawshiMaster mawshiMaster);
    Boolean updateMawshi(MawshiMaster mawshiMaster);
    Boolean deleteMawshi(MawshiMaster mawshiMaster);
    MawshiMaster getMawshi(Integer mawshiId);
    List<MawshiMaster> getAllMawshis();
    
    List<MawshiResponse> getMawshiRecords(Integer employeeId, String monthYear);
}
