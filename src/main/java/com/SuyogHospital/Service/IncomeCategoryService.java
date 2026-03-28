package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.IncomeCategoryMaster;


public interface IncomeCategoryService {
    Boolean addIncomeCategory(IncomeCategoryMaster incomeCategoryMaster);
    Boolean updateIncomeCategory(IncomeCategoryMaster incomeCategoryMaster);
    Boolean deleteIncomeCategory(IncomeCategoryMaster incomeCategoryMaster);
    IncomeCategoryMaster getIncomeCategory(Integer incomeCategoryId);
    List<IncomeCategoryMaster> getAllIncomeCategories();
}
