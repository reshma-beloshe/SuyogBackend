package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.MaritalStatusHistoryMaster;
import com.SuyogHospital.Master.MaritalStatusMaster;
import com.SuyogHospital.Repository.MaritalStatusDAO;
import com.SuyogHospital.Repository.MaritalStatusHistoryDAO;
import com.SuyogHospital.Service.MaritalStatusService;

@Service
public class MaritalStatusServiceImpl implements MaritalStatusService {

    private static final Logger logger = LogManager.getLogger(MaritalStatusServiceImpl.class);

    @Autowired
    private MaritalStatusDAO maritalStatusDAO;

    @Autowired
    private MaritalStatusHistoryDAO maritalStatusHistoryDAO;

    @Override
    public Boolean addMaritalStatus(MaritalStatusMaster maritalStatusMaster) {
        try {
            logger.info("In addMaritalStatus");

            LocalDateTime now = LocalDateTime.now();
            maritalStatusMaster.setDate(now.toLocalDate());
            maritalStatusMaster.setTime(now.toLocalTime());

            maritalStatusDAO.save(maritalStatusMaster);

            MaritalStatusHistoryMaster history = new MaritalStatusHistoryMaster();
            history.setMaritalStatusId(maritalStatusMaster.getMaritalStatusId());
            history.setMaritalStatus(maritalStatusMaster.getMaritalStatus());
            history.setDescription(maritalStatusMaster.getDescription());
            history.setIsApproved(maritalStatusMaster.getIsApproved());
            history.setApprovedBy(maritalStatusMaster.getApprovedBy());
            history.setAddedBy(maritalStatusMaster.getAddedBy());
            history.setUpdatedBy(maritalStatusMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            maritalStatusHistoryDAO.save(history);

            logger.info("Out of addMaritalStatus");
            return true;
        } catch (Exception e) {
            logger.error("Error in addMaritalStatus: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateMaritalStatus(MaritalStatusMaster maritalStatusMaster) {
        try {
            logger.info("In updateMaritalStatus");

            MaritalStatusMaster existing = maritalStatusDAO.findById(maritalStatusMaster.getMaritalStatusId())
                    .orElseThrow(() -> new RuntimeException("Marital Status not found"));

            LocalDateTime now = LocalDateTime.now();

            MaritalStatusHistoryMaster history = new MaritalStatusHistoryMaster();
            history.setMaritalStatusId(existing.getMaritalStatusId());
            history.setMaritalStatus(existing.getMaritalStatus());
            history.setDescription(existing.getDescription());
            history.setIsApproved(maritalStatusMaster.getIsApproved());
            history.setApprovedBy(maritalStatusMaster.getApprovedBy());
            history.setAddedBy(maritalStatusMaster.getAddedBy());
            history.setUpdatedBy(maritalStatusMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setMaritalStatus(maritalStatusMaster.getMaritalStatus());
            existing.setDescription(maritalStatusMaster.getDescription());
            existing.setIsApproved(maritalStatusMaster.getIsApproved());
            existing.setApprovedBy(maritalStatusMaster.getApprovedBy());
            existing.setAddedBy(maritalStatusMaster.getAddedBy());
            existing.setUpdatedBy(maritalStatusMaster.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            maritalStatusDAO.save(existing);
            maritalStatusHistoryDAO.save(history);
            logger.info("Out of updateMaritalStatus");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateMaritalStatus: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteMaritalStatus(MaritalStatusMaster maritalStatusMaster) {
        try {
            logger.info("In deleteMaritalStatus");

            MaritalStatusMaster existing = maritalStatusDAO.findById(maritalStatusMaster.getMaritalStatusId())
                    .orElseThrow(() -> new RuntimeException("Marital Status not found"));

            MaritalStatusHistoryMaster history = new MaritalStatusHistoryMaster();
            history.setMaritalStatusId(existing.getMaritalStatusId());
            history.setMaritalStatus(existing.getMaritalStatus());
            history.setDescription(existing.getDescription());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            maritalStatusDAO.delete(existing);
            maritalStatusHistoryDAO.save(history);
            logger.info("Out of deleteMaritalStatus");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteMaritalStatus: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public MaritalStatusMaster getMaritalStatus(Integer maritalStatusId) {
        try {
            logger.info("In getMaritalStatus");
            MaritalStatusMaster maritalStatusMaster = maritalStatusDAO.findById(maritalStatusId).orElse(null);
            logger.info("Out of getMaritalStatus");
            return maritalStatusMaster;
        } catch (Exception e) {
            logger.error("Error in getMaritalStatus: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<MaritalStatusMaster> getAllMaritalStatuses() {
        List<MaritalStatusMaster> allStatuses = new ArrayList<>();
        try {
            logger.info("In getAllMaritalStatuses");
            allStatuses = (List<MaritalStatusMaster>) maritalStatusDAO.findAll();
            logger.info("Out of getAllMaritalStatuses");
        } catch (Exception e) {
            logger.error("Error in getAllMaritalStatuses: " + e.getMessage(), e);
        }
        return allStatuses;
    }
}
