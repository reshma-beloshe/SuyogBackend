package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.DeductionHistoryMaster;
import com.SuyogHospital.Master.DeductionMaster;
import com.SuyogHospital.Repository.DeductionDAO;
import com.SuyogHospital.Repository.DeductionHistoryDAO;
import com.SuyogHospital.Service.DeductionService;

@Service
public class DeductionServiceImpl implements DeductionService {

    private static final Logger logger = LogManager.getLogger(DeductionServiceImpl.class);

    @Autowired
    private DeductionDAO deductionDAO;

    @Autowired
    private DeductionHistoryDAO deductionHistoryDAO;

    @Override
    public Boolean addDeductionService(DeductionMaster deductionMaster) {
        try {
            logger.info("In addDeductionService");
            LocalDateTime now = LocalDateTime.now();
            deductionMaster.setDate(now.toLocalDate());
            deductionMaster.setTime(now.toLocalTime());

            deductionDAO.save(deductionMaster);

            DeductionHistoryMaster history = new DeductionHistoryMaster();
            history.setDeductionId(deductionMaster.getDeductionId());
            history.setDeductionName(deductionMaster.getDeductionName());
            history.setDeductionType(deductionMaster.getDeductionType());
            history.setDeductionAmount(deductionMaster.getDeductionAmount());
            history.setDeductionDescription(deductionMaster.getDeductionDescription());
            history.setIsApproved(deductionMaster.getIsApproved());
            history.setApprovedBy(deductionMaster.getApprovedBy());
            history.setAddedBy(deductionMaster.getAddedBy());
            history.setUpdatedBy(deductionMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            deductionHistoryDAO.save(history);
            logger.info("Out of addDeductionService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateDeductionService(DeductionMaster deductionMaster) {
        try {
            logger.info("In updateDeductionService");

            DeductionMaster existing = deductionDAO.findById(deductionMaster.getDeductionId())
                    .orElseThrow(() -> new RuntimeException("Deduction not found"));

            LocalDateTime now = LocalDateTime.now();

            DeductionHistoryMaster history = new DeductionHistoryMaster();
            history.setDeductionId(existing.getDeductionId());
            history.setDeductionName(existing.getDeductionName());
            history.setDeductionType(existing.getDeductionType());
            history.setDeductionAmount(existing.getDeductionAmount());
            history.setDeductionDescription(existing.getDeductionDescription());
            history.setIsApproved(deductionMaster.getIsApproved());
            history.setApprovedBy(deductionMaster.getApprovedBy());
            history.setAddedBy(deductionMaster.getAddedBy());
            history.setUpdatedBy(deductionMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setDeductionName(deductionMaster.getDeductionName());
            existing.setDeductionType(deductionMaster.getDeductionType());
            existing.setDeductionAmount(deductionMaster.getDeductionAmount());
            existing.setDeductionDescription(deductionMaster.getDeductionDescription());
            existing.setIsApproved(deductionMaster.getIsApproved());
            existing.setApprovedBy(deductionMaster.getApprovedBy());
            existing.setAddedBy(deductionMaster.getAddedBy());
            existing.setUpdatedBy(deductionMaster.getUpdatedBy());

            deductionDAO.save(existing);
            deductionHistoryDAO.save(history);
            logger.info("Out of updateDeductionService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteDeductionService(DeductionMaster deductionMaster) {
        try {
            logger.info("In deleteDeductionService");

            DeductionMaster existing = deductionDAO.findById(deductionMaster.getDeductionId())
                    .orElseThrow(() -> new RuntimeException("Deduction not found"));

            DeductionHistoryMaster history = new DeductionHistoryMaster();
            history.setDeductionId(existing.getDeductionId());
            history.setDeductionName(existing.getDeductionName());
            history.setDeductionType(existing.getDeductionType());
            history.setDeductionAmount(existing.getDeductionAmount());
            history.setDeductionDescription(existing.getDeductionDescription());
            history.setIsApproved(deductionMaster.getIsApproved());
            history.setApprovedBy(deductionMaster.getApprovedBy());
            history.setAddedBy(deductionMaster.getAddedBy());
            history.setUpdatedBy(deductionMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            deductionDAO.delete(existing);
            deductionHistoryDAO.save(history);
            logger.info("Out of deleteDeductionService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public DeductionMaster getDeductionService(Integer deductionId) {
        try {
            logger.info("In getDeductionService");
            DeductionMaster deductionMaster = deductionDAO.findById(deductionId).orElse(null);
            logger.info("Out of getDeductionService");
            return deductionMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<DeductionMaster> getAllDeductionService() {
        List<DeductionMaster> allDeductions = new ArrayList<>();
        try {
            logger.info("In getAllDeductionService");
            allDeductions = (List<DeductionMaster>) deductionDAO.findAll();
            logger.info("Out of getAllDeductionService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allDeductions;
    }
}
