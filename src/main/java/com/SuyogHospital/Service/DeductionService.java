package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.DeductionMaster;

public interface DeductionService {

    Boolean addDeductionService(DeductionMaster deductionMaster);
    Boolean updateDeductionService(DeductionMaster deductionMaster);
    Boolean deleteDeductionService(DeductionMaster deductionMaster);
    DeductionMaster getDeductionService(Integer deductionId);
    List<DeductionMaster> getAllDeductionService();
}
