package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.PatientCategoryMaster;

public interface PatientCategoryService {
    Boolean addPatientCategoryService(PatientCategoryMaster patientCategoryMaster);
    Boolean updatePatientCategoryService(PatientCategoryMaster patientCategoryMaster);
    Boolean deletePatientCategoryService(PatientCategoryMaster patientCategoryMaster);
    PatientCategoryMaster getPatientCategoryService(Integer patientCategoryId);
    List<PatientCategoryMaster> getAllPatientCategoryService();
}
