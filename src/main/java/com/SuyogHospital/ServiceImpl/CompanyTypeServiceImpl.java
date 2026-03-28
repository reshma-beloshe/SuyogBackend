package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.CompanyTypeHistoryMaster;
import com.SuyogHospital.Master.CompanyTypeMaster;
import com.SuyogHospital.Repository.CompanyTypeDAO;
import com.SuyogHospital.Repository.CompanyTypeHistoryDAO;
import com.SuyogHospital.Service.CompanyTypeService;

@Service
public class CompanyTypeServiceImpl implements CompanyTypeService {

    private static final Logger logger = LogManager.getLogger(CompanyTypeServiceImpl.class);

    @Autowired
    private CompanyTypeDAO companyTypeDAO;

    @Autowired
    private CompanyTypeHistoryDAO companyTypeHistoryDAO;

    @Override
    public Boolean addCompanyType(CompanyTypeMaster companyTypeMaster) {
        try {
            logger.info("In addCompanyType");

            LocalDateTime now = LocalDateTime.now();
            companyTypeMaster.setDate(now.toLocalDate());
            companyTypeMaster.setTime(now.toLocalTime());

            companyTypeDAO.save(companyTypeMaster);

            CompanyTypeHistoryMaster history = new CompanyTypeHistoryMaster();
            history.setCompanyTypeId(companyTypeMaster.getCompanyTypeId());
            history.setCompanyTypeName(companyTypeMaster.getCompanyTypeName());
            history.setCompanyTypeDescription(companyTypeMaster.getCompanyTypeDescription());
            history.setCompanyTypeStatus(companyTypeMaster.getCompanyTypeStatus());
            history.setIsApproved(companyTypeMaster.getIsApproved());
            history.setApprovedBy(companyTypeMaster.getApprovedBy());
            history.setAddedBy(companyTypeMaster.getAddedBy());
            history.setUpdatedBy(companyTypeMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            companyTypeHistoryDAO.save(history);

            logger.info("Out of addCompanyType");
            return true;
        } catch (Exception e) {
            logger.error("Error in addCompanyType: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateCompanyType(CompanyTypeMaster companyTypeMaster) {
        try {
            logger.info("In updateCompanyType");

            CompanyTypeMaster existing = companyTypeDAO.findById(companyTypeMaster.getCompanyTypeId())
                    .orElseThrow(() -> new RuntimeException("Company Type not found"));

            LocalDateTime now = LocalDateTime.now();

            CompanyTypeHistoryMaster history = new CompanyTypeHistoryMaster();
            history.setCompanyTypeId(existing.getCompanyTypeId());
            history.setCompanyTypeName(existing.getCompanyTypeName());
            history.setCompanyTypeDescription(existing.getCompanyTypeDescription());
            history.setCompanyTypeStatus(existing.getCompanyTypeStatus());
            history.setIsApproved(companyTypeMaster.getIsApproved());
            history.setApprovedBy(companyTypeMaster.getApprovedBy());
            history.setAddedBy(companyTypeMaster.getAddedBy());
            history.setUpdatedBy(companyTypeMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setCompanyTypeName(companyTypeMaster.getCompanyTypeName());
            existing.setCompanyTypeDescription(companyTypeMaster.getCompanyTypeDescription());
            existing.setCompanyTypeStatus(companyTypeMaster.getCompanyTypeStatus());
            existing.setIsApproved(companyTypeMaster.getIsApproved());
            existing.setApprovedBy(companyTypeMaster.getApprovedBy());
            existing.setAddedBy(companyTypeMaster.getAddedBy());
            existing.setUpdatedBy(companyTypeMaster.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            companyTypeDAO.save(existing);
            companyTypeHistoryDAO.save(history);

            logger.info("Out of updateCompanyType");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateCompanyType: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteCompanyType(CompanyTypeMaster companyTypeMaster) {
        try {
            logger.info("In deleteCompanyType");

            CompanyTypeMaster existing = companyTypeDAO.findById(companyTypeMaster.getCompanyTypeId())
                    .orElseThrow(() -> new RuntimeException("Company Type not found"));

            CompanyTypeHistoryMaster history = new CompanyTypeHistoryMaster();
            history.setCompanyTypeId(existing.getCompanyTypeId());
            history.setCompanyTypeName(existing.getCompanyTypeName());
            history.setCompanyTypeDescription(existing.getCompanyTypeDescription());
            history.setCompanyTypeStatus(existing.getCompanyTypeStatus());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            companyTypeDAO.delete(existing);
            companyTypeHistoryDAO.save(history);

            logger.info("Out of deleteCompanyType");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteCompanyType: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public CompanyTypeMaster getCompanyType(Integer companyTypeId) {
        try {
            logger.info("In getCompanyType");
            CompanyTypeMaster companyTypeMaster = companyTypeDAO.findById(companyTypeId).orElse(null);
            logger.info("Out of getCompanyType");
            return companyTypeMaster;
        } catch (Exception e) {
            logger.error("Error in getCompanyType: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<CompanyTypeMaster> getAllCompanyTypes() {
        List<CompanyTypeMaster> allCompanyTypes = new ArrayList<>();
        try {
            logger.info("In getAllCompanyTypes");
            allCompanyTypes = (List<CompanyTypeMaster>) companyTypeDAO.findAll();
            logger.info("Out of getAllCompanyTypes");
        } catch (Exception e) {
            logger.error("Error in getAllCompanyTypes: " + e.getMessage(), e);
        }
        return allCompanyTypes;
    }
}
