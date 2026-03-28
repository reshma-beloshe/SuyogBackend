package com.SuyogHospital.Service;

import java.util.List;
import com.SuyogHospital.Master.SpecialisationMaster;

public interface SpecialisationService {
    Boolean addSpecialisationService(SpecialisationMaster specialisationMaster);
    Boolean updateSpecialisationService(SpecialisationMaster specialisationMaster);
    Boolean deleteSpecialisationService(SpecialisationMaster specialisationMaster);
    SpecialisationMaster getSpecialisationService(Integer specialisationId);
    List<SpecialisationMaster> getAllSpecialisationService();
}
