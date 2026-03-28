package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.MaritalStatusMaster;

public interface MaritalStatusService {
    Boolean addMaritalStatus(MaritalStatusMaster maritalStatusMaster);
    Boolean updateMaritalStatus(MaritalStatusMaster maritalStatusMaster);
    Boolean deleteMaritalStatus(MaritalStatusMaster maritalStatusMaster);
    MaritalStatusMaster getMaritalStatus(Integer maritalStatusId);
    List<MaritalStatusMaster> getAllMaritalStatuses();
}
