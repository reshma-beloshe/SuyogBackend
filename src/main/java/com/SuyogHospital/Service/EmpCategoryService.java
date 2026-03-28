package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.EmpCategoryMaster;

public interface EmpCategoryService {

	public Boolean addEmpCategory(EmpCategoryMaster empCategoryMaster);
	public Boolean updateEmpCategory(EmpCategoryMaster empCategoryMaster);
	public Boolean deleteEmpCategory(EmpCategoryMaster empCategoryMaster);
	public EmpCategoryMaster getEmpCategory(Integer EmpCategoryId);
	public List<EmpCategoryMaster> getAllEmpCategories();
	
}
