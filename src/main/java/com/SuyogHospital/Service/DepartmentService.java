package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.DepartmentMaster;

public interface DepartmentService {

    public Boolean addDepartmentService(DepartmentMaster departmentMaster);
    public Boolean updateDepartmentService(DepartmentMaster departmentMaster);
    public Boolean deleteDepartmentService(DepartmentMaster departmentMaster);
    public DepartmentMaster getDepartmentService(Integer departmentId);
    public List<DepartmentMaster> getAllDepartmentService();
}
