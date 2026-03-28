package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.EmpCategoryHistoryMaster;
import com.SuyogHospital.Master.EmpCategoryMaster;
import com.SuyogHospital.Repository.EmpCategoryDAO;
import com.SuyogHospital.Repository.EmpCategoryHistoryDAO;
import com.SuyogHospital.Service.EmpCategoryService;

@Service
public class EmpCategoryServiceImpl implements EmpCategoryService {

    private static final Logger logger = LogManager.getLogger(EmpCategoryServiceImpl.class);

    @Autowired
    private EmpCategoryDAO empCategoryDAO;

    @Autowired
    private EmpCategoryHistoryDAO empCategoryHistoryDAO;

    @Override
    public Boolean addEmpCategory(EmpCategoryMaster empCategory) {
        try {
            logger.info("In addEmpCategory");

            LocalDateTime now = LocalDateTime.now();
            empCategory.setDate(now.toLocalDate());
            empCategory.setTime(now.toLocalTime());

            empCategoryDAO.save(empCategory);

            EmpCategoryHistoryMaster history = new EmpCategoryHistoryMaster();
            history.setEmpCategoryId(empCategory.getEmpCategoryId());
            history.setEmpCategoryName(empCategory.getEmpCategoryName());
            history.setEmpCategoryDescription(empCategory.getEmpCategoryDescription());
            history.setAbbreviation(empCategory.getAbbreviation());
            history.setStatus(empCategory.getStatus());
            history.setIsApproved(empCategory.getIsApproved());
            history.setApprovedBy(empCategory.getApprovedBy());
            history.setAddedBy(empCategory.getAddedBy());
            history.setUpdatedBy(empCategory.getUpdatedBy());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            empCategoryHistoryDAO.save(history);
            logger.info("Out of addEmpCategory");
            return true;
        } catch (Exception e) {
            logger.error("Error in addEmpCategory: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateEmpCategory(EmpCategoryMaster empCategory) {
        try {
            logger.info("In updateEmpCategory");

            EmpCategoryMaster existing = empCategoryDAO.findById(empCategory.getEmpCategoryId())
                    .orElseThrow(() -> new RuntimeException("EmpCategory not found"));

            LocalDateTime now = LocalDateTime.now();

            EmpCategoryHistoryMaster history = new EmpCategoryHistoryMaster();
            history.setEmpCategoryId(existing.getEmpCategoryId());
            history.setEmpCategoryName(existing.getEmpCategoryName());
            history.setEmpCategoryDescription(existing.getEmpCategoryDescription());
            history.setAbbreviation(existing.getAbbreviation());
            history.setStatus(existing.getStatus());
            history.setIsApproved(empCategory.getIsApproved());
            history.setApprovedBy(empCategory.getApprovedBy());
            history.setAddedBy(empCategory.getAddedBy());
            history.setUpdatedBy(empCategory.getUpdatedBy());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            existing.setEmpCategoryName(empCategory.getEmpCategoryName());
            existing.setEmpCategoryDescription(empCategory.getEmpCategoryDescription());
            existing.setAbbreviation(empCategory.getAbbreviation());
            existing.setStatus(empCategory.getStatus());
            existing.setIsApproved(empCategory.getIsApproved());
            existing.setApprovedBy(empCategory.getApprovedBy());
            existing.setAddedBy(empCategory.getAddedBy());
            existing.setUpdatedBy(empCategory.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            empCategoryDAO.save(existing);
            empCategoryHistoryDAO.save(history);
            logger.info("Out of updateEmpCategory");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateEmpCategory: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteEmpCategory(EmpCategoryMaster empCategory) {
        try {
            logger.info("In deleteEmpCategory");
            
            EmpCategoryMaster existing = empCategoryDAO.findById(empCategory.getEmpCategoryId())
                    .orElseThrow(() -> new RuntimeException("EmpCategory not found"));

            EmpCategoryHistoryMaster history = new EmpCategoryHistoryMaster();
            history.setEmpCategoryId(existing.getEmpCategoryId());
            history.setEmpCategoryName(existing.getEmpCategoryName());
            history.setEmpCategoryDescription(existing.getEmpCategoryDescription());
            history.setAbbreviation(existing.getAbbreviation());
            history.setStatus(existing.getStatus());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            empCategoryDAO.delete(existing);
            empCategoryHistoryDAO.save(history);
            logger.info("Out of deleteEmpCategory");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteEmpCategory: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public EmpCategoryMaster getEmpCategory(Integer empCategoryId) {
        try {
            logger.info("In getEmpCategory");
            EmpCategoryMaster empCategory = empCategoryDAO.findById(empCategoryId).orElse(null);
            logger.info("Out of getEmpCategory");
            return empCategory;
        } catch (Exception e) {
            logger.error("Error in getEmpCategory: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<EmpCategoryMaster> getAllEmpCategories() {
        List<EmpCategoryMaster> allCategories = new ArrayList<>();
        try {
            logger.info("In getAllEmpCategories");
            allCategories = (List<EmpCategoryMaster>) empCategoryDAO.findAll();
            logger.info("Out of getAllEmpCategories");
        } catch (Exception e) {
            logger.error("Error in getAllEmpCategories: " + e.getMessage(), e);
        }
        return allCategories;
    }
}
