package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.IncomeCategoryHistoryMaster;
import com.SuyogHospital.Master.IncomeCategoryMaster;
import com.SuyogHospital.Repository.IncomeCategoryDAO;
import com.SuyogHospital.Repository.IncomeCategoryHistoryDAO;
import com.SuyogHospital.Service.IncomeCategoryService;

@Service
public class IncomeCategoryServiceImpl implements IncomeCategoryService {

    private static final Logger logger = LogManager.getLogger(IncomeCategoryServiceImpl.class);

    @Autowired
    private IncomeCategoryDAO incomeCategoryDAO;

    @Autowired
    private IncomeCategoryHistoryDAO incomeCategoryHistoryDAO;

    @Override
    public Boolean addIncomeCategory(IncomeCategoryMaster incomeCategoryMaster) {
        try {
            logger.info("In addIncomeCategory");

            LocalDateTime now = LocalDateTime.now();
            incomeCategoryMaster.setDate(now.toLocalDate());
            incomeCategoryMaster.setTime(now.toLocalTime());

            incomeCategoryDAO.save(incomeCategoryMaster);

            IncomeCategoryHistoryMaster history = new IncomeCategoryHistoryMaster();
            history.setInComeId(incomeCategoryMaster.getInComeId());
            history.setIncomeDesc(incomeCategoryMaster.getIncomeDesc());
            history.setInComeFrom(incomeCategoryMaster.getInComeFrom());
            history.setInComeTo(incomeCategoryMaster.getInComeTo());
            history.setIsApproved(incomeCategoryMaster.getIsApproved());
            history.setApprovedBy(incomeCategoryMaster.getApprovedBy());
            history.setAddedBy(incomeCategoryMaster.getAddedBy());
            history.setUpdatedBy(incomeCategoryMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            incomeCategoryHistoryDAO.save(history);

            logger.info("Out of addIncomeCategory");
            return true;
        } catch (Exception e) {
            logger.error("Error in addIncomeCategory: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateIncomeCategory(IncomeCategoryMaster incomeCategoryMaster) {
        try {
            logger.info("In updateIncomeCategory");

            IncomeCategoryMaster existing = incomeCategoryDAO.findById(incomeCategoryMaster.getInComeId())
                    .orElseThrow(() -> new RuntimeException("Income Category not found"));

            LocalDateTime now = LocalDateTime.now();

            IncomeCategoryHistoryMaster history = new IncomeCategoryHistoryMaster();
            history.setInComeId(existing.getInComeId());
            history.setIncomeDesc(existing.getIncomeDesc());
            history.setInComeFrom(existing.getInComeFrom());
            history.setInComeTo(existing.getInComeTo());
            history.setIsApproved(incomeCategoryMaster.getIsApproved());
            history.setApprovedBy(incomeCategoryMaster.getApprovedBy());
            history.setAddedBy(incomeCategoryMaster.getAddedBy());
            history.setUpdatedBy(incomeCategoryMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setIncomeDesc(incomeCategoryMaster.getIncomeDesc());
            existing.setInComeFrom(incomeCategoryMaster.getInComeFrom());
            existing.setInComeTo(incomeCategoryMaster.getInComeTo());
            existing.setIsApproved(incomeCategoryMaster.getIsApproved());
            existing.setApprovedBy(incomeCategoryMaster.getApprovedBy());
            existing.setAddedBy(incomeCategoryMaster.getAddedBy());
            existing.setUpdatedBy(incomeCategoryMaster.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            incomeCategoryDAO.save(existing);
            incomeCategoryHistoryDAO.save(history);

            logger.info("Out of updateIncomeCategory");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateIncomeCategory: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteIncomeCategory(IncomeCategoryMaster incomeCategoryMaster) {
        try {
            logger.info("In deleteIncomeCategory");

            IncomeCategoryMaster existing = incomeCategoryDAO.findById(incomeCategoryMaster.getInComeId())
                    .orElseThrow(() -> new RuntimeException("Income Category not found"));

            IncomeCategoryHistoryMaster history = new IncomeCategoryHistoryMaster();
            history.setInComeId(existing.getInComeId());
            history.setIncomeDesc(existing.getIncomeDesc());
            history.setInComeFrom(existing.getInComeFrom());
            history.setInComeTo(existing.getInComeTo());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            incomeCategoryDAO.delete(existing);
            incomeCategoryHistoryDAO.save(history);

            logger.info("Out of deleteIncomeCategory");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteIncomeCategory: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public IncomeCategoryMaster getIncomeCategory(Integer incomeCategoryId) {
        try {
            logger.info("In getIncomeCategory");
            IncomeCategoryMaster incomeCategoryMaster = incomeCategoryDAO.findById(incomeCategoryId).orElse(null);
            logger.info("Out of getIncomeCategory");
            return incomeCategoryMaster;
        } catch (Exception e) {
            logger.error("Error in getIncomeCategory: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<IncomeCategoryMaster> getAllIncomeCategories() {
        List<IncomeCategoryMaster> allCategories = new ArrayList<>();
        try {
            logger.info("In getAllIncomeCategories");
            allCategories = (List<IncomeCategoryMaster>) incomeCategoryDAO.findAll();
            logger.info("Out of getAllIncomeCategories");
        } catch (Exception e) {
            logger.error("Error in getAllIncomeCategories: " + e.getMessage(), e);
        }
        return allCategories;
    }
}
