package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.ProcedureTypeMaster;

public interface ProcedureTypeService {
    Boolean addProcedureType(ProcedureTypeMaster procedureTypeMaster);
    Boolean updateProcedureType(ProcedureTypeMaster procedureTypeMaster);
    Boolean deleteProcedureType(ProcedureTypeMaster procedureTypeMaster);
    ProcedureTypeMaster getProcedureType(Integer procedureTypeId);
    List<ProcedureTypeMaster> getAllProcedureTypes();
}
