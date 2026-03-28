package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.CompanyTypeMaster;

public interface CompanyTypeService {
    Boolean addCompanyType(CompanyTypeMaster companyTypeMaster);
    Boolean updateCompanyType(CompanyTypeMaster companyTypeMaster);
    Boolean deleteCompanyType(CompanyTypeMaster companyTypeMaster);
    CompanyTypeMaster getCompanyType(Integer companyTypeId);
    List<CompanyTypeMaster> getAllCompanyTypes();
}
