package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.DoctorTypeMaster;

public interface DoctorTypeService {
    
    public Boolean addDoctorTypeService(DoctorTypeMaster doctorTypeMaster);
    public Boolean updateDoctorTypeService(DoctorTypeMaster doctorTypeMaster);
    public Boolean deleteDoctorTypeService(DoctorTypeMaster doctorTypeMaster);
    public DoctorTypeMaster getDoctorTypeService(Integer doctorTypeId);
    public List<DoctorTypeMaster> getAllDoctorTypeService();
}
