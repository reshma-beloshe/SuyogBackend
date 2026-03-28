package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.DesignationHistoryMaster;
import com.SuyogHospital.Master.DesignationMaster;
import com.SuyogHospital.Repository.DesignationDAO;
import com.SuyogHospital.Repository.DesignationHistoryDAO;
import com.SuyogHospital.Service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {

    private static final Logger logger = LogManager.getLogger(DesignationServiceImpl.class);

    @Autowired
    private DesignationDAO designationDAO;

    @Autowired
    private DesignationHistoryDAO designationHistoryDAO;

    @Override
    public Boolean addDesignationService(DesignationMaster designationMaster) {
        try {
            logger.info("In addDesignationService");

            LocalDateTime now = LocalDateTime.now();
            designationMaster.setDate(now.toLocalDate());
            designationMaster.setTime(now.toLocalTime());

            designationDAO.save(designationMaster);

            DesignationHistoryMaster history = new DesignationHistoryMaster();
            history.setDesignationId(designationMaster.getId());
            history.setDesignationName(designationMaster.getDesignationName());
            history.setDescription(designationMaster.getDescription());
            history.setStatus(designationMaster.getStatus());
            history.setIsApproved(designationMaster.getIsApproved());
            history.setApprovedBy(designationMaster.getApprovedBy());
            history.setAddedBy(designationMaster.getAddedBy());
            history.setUpdatedBy(designationMaster.getUpdatedBy());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            designationHistoryDAO.save(history);
            logger.info("Out of addDesignationService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateDesignationService(DesignationMaster designationMaster) {
        try {
            logger.info("In updateDesignationService");

            DesignationMaster existing = designationDAO.findById(designationMaster.getId())
                    .orElseThrow(() -> new RuntimeException("Designation not found"));

            LocalDateTime now = LocalDateTime.now();

            DesignationHistoryMaster history = new DesignationHistoryMaster();
            history.setDesignationId(existing.getId());
            history.setDesignationName(existing.getDesignationName());
            history.setDescription(existing.getDescription());
            history.setStatus(existing.getStatus());
            history.setIsApproved(designationMaster.getIsApproved());
            history.setApprovedBy(designationMaster.getApprovedBy());
            history.setAddedBy(designationMaster.getAddedBy());
            history.setUpdatedBy(designationMaster.getUpdatedBy());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            existing.setDesignationName(designationMaster.getDesignationName());
            existing.setDescription(designationMaster.getDescription());
            existing.setStatus(designationMaster.getStatus());
            existing.setIsApproved(designationMaster.getIsApproved());
            existing.setApprovedBy(designationMaster.getApprovedBy());
            existing.setAddedBy(designationMaster.getAddedBy());
            existing.setUpdatedBy(designationMaster.getUpdatedBy());

            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            designationDAO.save(existing);
            designationHistoryDAO.save(history);
            logger.info("Out of updateDesignationService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteDesignationService(DesignationMaster designationMaster) {
        try {
            logger.info("In deleteDesignationService");

            DesignationMaster existing = designationDAO.findById(designationMaster.getId())
                    .orElseThrow(() -> new RuntimeException("Designation not found"));

            DesignationHistoryMaster history = new DesignationHistoryMaster();
            history.setDesignationId(existing.getId());
            history.setDesignationName(existing.getDesignationName());
            history.setDescription(existing.getDescription());
            history.setStatus(existing.getStatus());
            history.setIsApproved(designationMaster.getIsApproved());
            history.setApprovedBy(designationMaster.getApprovedBy());
            history.setAddedBy(designationMaster.getAddedBy());
            history.setUpdatedBy(designationMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            designationDAO.delete(existing);
            designationHistoryDAO.save(history);
            logger.info("Out of deleteDesignationService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public DesignationMaster getDesignationService(Integer designationId) {
        try {
            logger.info("In getDesignationService");
            DesignationMaster designationMaster = designationDAO.findById(designationId).orElse(null);
            logger.info("Out of getDesignationService");
            return designationMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<DesignationMaster> getAllDesignationService() {
        List<DesignationMaster> allDesignations = new ArrayList<>();
        try {
            logger.info("In getAllDesignationService");
            allDesignations = (List<DesignationMaster>) designationDAO.findAll();
            logger.info("Out of getAllDesignationService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allDesignations;
    }
}
