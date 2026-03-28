package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.DesignationMaster;

public interface DesignationService {
    Boolean addDesignationService(DesignationMaster designationMaster);
    Boolean updateDesignationService(DesignationMaster designationMaster);
    Boolean deleteDesignationService(DesignationMaster designationMaster);
    DesignationMaster getDesignationService(Integer designationId);
    List<DesignationMaster> getAllDesignationService();
}
