package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.ShiftHistoryMaster;
import com.SuyogHospital.Master.ShiftMaster;
import com.SuyogHospital.Repository.ShiftDAO;
import com.SuyogHospital.Repository.ShiftHistoryDAO;
import com.SuyogHospital.Service.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService {

    private static final Logger logger = LogManager.getLogger(ShiftServiceImpl.class);

    @Autowired
    private ShiftDAO shiftDAO;
    
    @Autowired
    private ShiftHistoryDAO shiftHistoryDAO;

    @Override
    public Boolean addShiftService(ShiftMaster shiftMaster) {
        try {
            logger.info("In addShiftService");
            LocalDateTime now = LocalDateTime.now();
            shiftMaster.setDate(now.toLocalDate());
            shiftMaster.setTime(now.toLocalTime());

            shiftDAO.save(shiftMaster);

            // Save history record
            ShiftHistoryMaster history = new ShiftHistoryMaster();
            history.setShiftId(shiftMaster.getShiftId());
            history.setShiftName(shiftMaster.getShiftName());
            history.setShiftDesc(shiftMaster.getShiftDesc());
            history.setIsApproved(shiftMaster.getIsApproved());
            history.setApprovedBy(shiftMaster.getApprovedBy());
            history.setAddedBy(shiftMaster.getAddedBy());
            history.setUpdatedBy(shiftMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            shiftHistoryDAO.save(history);

            logger.info("Out of addShiftService");
            return true;
        } catch (Exception e) {
            logger.error("Error in addShiftService: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateShiftService(ShiftMaster shiftMaster) {
        try {
            logger.info("In updateShiftService");

            ShiftMaster existing = shiftDAO.findById(shiftMaster.getShiftId())
                    .orElseThrow(() -> new RuntimeException("Shift not found"));

            LocalDateTime now = LocalDateTime.now();

            // Create history record
            ShiftHistoryMaster history = new ShiftHistoryMaster();
            history.setShiftId(existing.getShiftId());
            history.setShiftName(existing.getShiftName());
            history.setShiftDesc(existing.getShiftDesc());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            // Update existing shift record
            existing.setShiftName(shiftMaster.getShiftName());
            existing.setShiftDesc(shiftMaster.getShiftDesc());
            existing.setIsApproved(shiftMaster.getIsApproved());
            existing.setApprovedBy(shiftMaster.getApprovedBy());
            existing.setAddedBy(shiftMaster.getAddedBy());
            existing.setUpdatedBy(shiftMaster.getUpdatedBy());

            shiftDAO.save(existing);
            shiftHistoryDAO.save(history);

            logger.info("Out of updateShiftService");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateShiftService: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteShiftService(ShiftMaster shiftMaster) {
        try {
            logger.info("In deleteShiftService");

            ShiftMaster existing = shiftDAO.findById(shiftMaster.getShiftId())
                    .orElseThrow(() -> new RuntimeException("Shift not found"));

            ShiftHistoryMaster history = new ShiftHistoryMaster();
            history.setShiftId(existing.getShiftId());
            history.setShiftName(existing.getShiftName());
            history.setShiftDesc(existing.getShiftDesc());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            shiftDAO.delete(existing);
            shiftHistoryDAO.save(history);

            logger.info("Out of deleteShiftService");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteShiftService: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public ShiftMaster getShiftService(Integer shiftId) {
        try {
            logger.info("In getShiftService");
            ShiftMaster shiftMaster = shiftDAO.findById(shiftId).orElse(null);
            logger.info("Out of getShiftService");
            return shiftMaster;
        } catch (Exception e) {
            logger.error("Error in getShiftService: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<ShiftMaster> getAllShiftService() {
        List<ShiftMaster> allShifts = new ArrayList<>();
        try {
            logger.info("In getAllShiftService");
            allShifts = (List<ShiftMaster>) shiftDAO.findAll();
            logger.info("Out of getAllShiftService");
        } catch (Exception e) {
            logger.error("Error in getAllShiftService: " + e.getMessage(), e);
        }
        return allShifts;
    }
}
