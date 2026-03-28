package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.AllowanceHistoryMaster;
import com.SuyogHospital.Master.AllowanceMaster;
import com.SuyogHospital.Repository.AllowanceDAO;
import com.SuyogHospital.Repository.AllowanceHistoryDAO;
import com.SuyogHospital.Service.AllowanceService;

@Service
public class AllowanceServiceImpl implements AllowanceService {

    private static final Logger logger = LogManager.getLogger(AllowanceServiceImpl.class);

    @Autowired
    private AllowanceDAO allowanceDAO;

    @Autowired
    private AllowanceHistoryDAO allowanceHistoryDAO;

    @Override
    public Boolean addAllowanceService(AllowanceMaster allowanceMaster) {
        try {
            logger.info("In addAllowanceService");
            LocalDateTime now = LocalDateTime.now();
            allowanceMaster.setDate(now.toLocalDate());
            allowanceMaster.setTime(now.toLocalTime());

            allowanceDAO.save(allowanceMaster);

            AllowanceHistoryMaster history = new AllowanceHistoryMaster();
            history.setAllowanceId(allowanceMaster.getAllowanceId());
            history.setAllowanceName(allowanceMaster.getAllowanceName());
            history.setAllowanceType(allowanceMaster.getAllowanceType());
            history.setAllowanceAmount(allowanceMaster.getAllowanceAmount());
            history.setAllowanceDescription(allowanceMaster.getAllowanceDescription());
            history.setIsApproved(allowanceMaster.getIsApproved());
            history.setApprovedBy(allowanceMaster.getApprovedBy());
            history.setAddedBy(allowanceMaster.getAddedBy());
            history.setUpdatedBy(allowanceMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            allowanceHistoryDAO.save(history);
            logger.info("Out of addAllowanceService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateAllowanceService(AllowanceMaster allowanceMaster) {
        try {
            logger.info("In updateAllowanceService");

            AllowanceMaster existing = allowanceDAO.findById(allowanceMaster.getAllowanceId())
                    .orElseThrow(() -> new RuntimeException("Allowance not found"));

            LocalDateTime now = LocalDateTime.now();

            AllowanceHistoryMaster history = new AllowanceHistoryMaster();
            history.setAllowanceId(existing.getAllowanceId());
            history.setAllowanceName(existing.getAllowanceName());
            history.setAllowanceType(existing.getAllowanceType());
            history.setAllowanceAmount(existing.getAllowanceAmount());
            history.setAllowanceDescription(existing.getAllowanceDescription());
            history.setIsApproved(allowanceMaster.getIsApproved());
            history.setApprovedBy(allowanceMaster.getApprovedBy());
            history.setAddedBy(allowanceMaster.getAddedBy());
            history.setUpdatedBy(allowanceMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setAllowanceName(allowanceMaster.getAllowanceName());
            existing.setAllowanceType(allowanceMaster.getAllowanceType());
            existing.setAllowanceAmount(allowanceMaster.getAllowanceAmount());
            existing.setAllowanceDescription(allowanceMaster.getAllowanceDescription());
            existing.setIsApproved(allowanceMaster.getIsApproved());
            existing.setApprovedBy(allowanceMaster.getApprovedBy());
            existing.setAddedBy(allowanceMaster.getAddedBy());
            existing.setUpdatedBy(allowanceMaster.getUpdatedBy());

            allowanceDAO.save(existing);
            allowanceHistoryDAO.save(history);
            logger.info("Out of updateAllowanceService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteAllowanceService(AllowanceMaster allowanceMaster) {
        try {
            logger.info("In deleteAllowanceService");

            AllowanceMaster existing = allowanceDAO.findById(allowanceMaster.getAllowanceId())
                    .orElseThrow(() -> new RuntimeException("Allowance not found"));

            AllowanceHistoryMaster history = new AllowanceHistoryMaster();
            history.setAllowanceId(existing.getAllowanceId());
            history.setAllowanceName(existing.getAllowanceName());
            history.setAllowanceType(existing.getAllowanceType());
            history.setAllowanceAmount(existing.getAllowanceAmount());
            history.setAllowanceDescription(existing.getAllowanceDescription());
            history.setIsApproved(allowanceMaster.getIsApproved());
            history.setApprovedBy(allowanceMaster.getApprovedBy());
            history.setAddedBy(allowanceMaster.getAddedBy());
            history.setUpdatedBy(allowanceMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            allowanceDAO.delete(existing);
            allowanceHistoryDAO.save(history);
            logger.info("Out of deleteAllowanceService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public AllowanceMaster getAllowanceService(Integer allowanceId) {
        try {
            logger.info("In getAllowanceService");
            AllowanceMaster allowanceMaster = allowanceDAO.findById(allowanceId).orElse(null);
            logger.info("Out of getAllowanceService");
            return allowanceMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<AllowanceMaster> getAllAllowanceService() {
        List<AllowanceMaster> allAllowances = new ArrayList<>();
        try {
            logger.info("In getAllAllowanceService");
            allAllowances = (List<AllowanceMaster>) allowanceDAO.findAll();
            logger.info("Out of getAllAllowanceService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allAllowances;
    }
}
