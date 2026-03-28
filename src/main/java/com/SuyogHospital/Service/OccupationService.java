package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.OccupationMaster;

public interface OccupationService {
    Boolean addOccupation(OccupationMaster occupationMaster);
    Boolean updateOccupation(OccupationMaster occupationMaster);
    Boolean deleteOccupation(OccupationMaster occupationMaster);
    OccupationMaster getOccupation(Integer occupationId);
    List<OccupationMaster> getAllOccupations();
}
