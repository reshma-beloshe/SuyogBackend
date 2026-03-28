package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.OccupationHistoryMaster;
import com.SuyogHospital.Master.OccupationMaster;
import com.SuyogHospital.Repository.OccupationDAO;
import com.SuyogHospital.Repository.OccupationHistoryDAO;
import com.SuyogHospital.Service.OccupationService;

@Service
public class OccupationServiceImpl implements OccupationService {

    private static final Logger logger = LogManager.getLogger(OccupationServiceImpl.class);

    @Autowired
    private OccupationDAO occupationDAO;

    @Autowired
    private OccupationHistoryDAO occupationHistoryDAO;

    @Override
    public Boolean addOccupation(OccupationMaster occupationMaster) {
        try {
            logger.info("In addOccupation");

            LocalDateTime now = LocalDateTime.now();
            occupationMaster.setDate(now.toLocalDate());
            occupationMaster.setTime(now.toLocalTime());

            occupationDAO.save(occupationMaster);

            OccupationHistoryMaster history = new OccupationHistoryMaster();
            history.setOccupationId(occupationMaster.getOccupationId());
            history.setOccupationName(occupationMaster.getOccupationName());
            history.setOccupationDescription(occupationMaster.getOccupationDescription());
            history.setIsApproved(occupationMaster.getIsApproved());
            history.setApprovedBy(occupationMaster.getApprovedBy());
            history.setAddedBy(occupationMaster.getAddedBy());
            history.setUpdatedBy(occupationMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            occupationHistoryDAO.save(history);

            logger.info("Out of addOccupation");
            return true;
        } catch (Exception e) {
            logger.error("Error in addOccupation: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateOccupation(OccupationMaster occupationMaster) {
        try {
            logger.info("In updateOccupation");

            OccupationMaster existing = occupationDAO.findById(occupationMaster.getOccupationId())
                    .orElseThrow(() -> new RuntimeException("Occupation not found"));

            LocalDateTime now = LocalDateTime.now();

            OccupationHistoryMaster history = new OccupationHistoryMaster();
            history.setOccupationId(existing.getOccupationId());
            history.setOccupationName(existing.getOccupationName());
            history.setOccupationDescription(existing.getOccupationDescription());
            history.setIsApproved(occupationMaster.getIsApproved());
            history.setApprovedBy(occupationMaster.getApprovedBy());
            history.setAddedBy(occupationMaster.getAddedBy());
            history.setUpdatedBy(occupationMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setOccupationName(occupationMaster.getOccupationName());
            existing.setOccupationDescription(occupationMaster.getOccupationDescription());
            existing.setIsApproved(occupationMaster.getIsApproved());
            existing.setApprovedBy(occupationMaster.getApprovedBy());
            existing.setAddedBy(occupationMaster.getAddedBy());
            existing.setUpdatedBy(occupationMaster.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            occupationDAO.save(existing);
            occupationHistoryDAO.save(history);

            logger.info("Out of updateOccupation");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateOccupation: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteOccupation(OccupationMaster occupationMaster) {
        try {
            logger.info("In deleteOccupation");

            OccupationMaster existing = occupationDAO.findById(occupationMaster.getOccupationId())
                    .orElseThrow(() -> new RuntimeException("Occupation not found"));

            OccupationHistoryMaster history = new OccupationHistoryMaster();
            history.setOccupationId(existing.getOccupationId());
            history.setOccupationName(existing.getOccupationName());
            history.setOccupationDescription(existing.getOccupationDescription());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            occupationDAO.delete(existing);
            occupationHistoryDAO.save(history);

            logger.info("Out of deleteOccupation");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteOccupation: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public OccupationMaster getOccupation(Integer occupationId) {
        try {
            logger.info("In getOccupation");
            OccupationMaster occupationMaster = occupationDAO.findById(occupationId).orElse(null);
            logger.info("Out of getOccupation");
            return occupationMaster;
        } catch (Exception e) {
            logger.error("Error in getOccupation: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<OccupationMaster> getAllOccupations() {
        List<OccupationMaster> allOccupations = new ArrayList<>();
        try {
            logger.info("In getAllOccupations");
            allOccupations = (List<OccupationMaster>) occupationDAO.findAll();
            logger.info("Out of getAllOccupations");
        } catch (Exception e) {
            logger.error("Error in getAllOccupations: " + e.getMessage(), e);
        }
        return allOccupations;
    }
}
