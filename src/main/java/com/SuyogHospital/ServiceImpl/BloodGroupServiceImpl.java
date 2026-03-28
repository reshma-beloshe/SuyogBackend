package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.BloodGroupHistoryMaster;
import com.SuyogHospital.Master.BloodGroupMaster;
import com.SuyogHospital.Repository.BloodGroupDAO;
import com.SuyogHospital.Repository.BloodGroupHistoryDAO;
import com.SuyogHospital.Service.BloodGroupService;

@Service
public class BloodGroupServiceImpl implements BloodGroupService {

    private static final Logger logger = LogManager.getLogger(BloodGroupServiceImpl.class);

    @Autowired
    private BloodGroupDAO bloodGroupDAO;

    @Autowired
    private BloodGroupHistoryDAO bloodGroupHistoryDAO;

    @Override
    public Boolean addBloodGroup(BloodGroupMaster bloodGroupMaster) {
        try {
            logger.info("In addBloodGroup");

            LocalDateTime now = LocalDateTime.now();
            bloodGroupMaster.setDate(now.toLocalDate());
            bloodGroupMaster.setTime(now.toLocalTime());

            bloodGroupDAO.save(bloodGroupMaster);

            BloodGroupHistoryMaster history = new BloodGroupHistoryMaster();
            history.setBloodGroupId(bloodGroupMaster.getBloodGroupId());
            history.setBloodGroupName(bloodGroupMaster.getBloodGroupName());
            history.setBloodDescription(bloodGroupMaster.getBloodDescription());
            history.setIsApproved(bloodGroupMaster.getIsApproved());
            history.setApprovedBy(bloodGroupMaster.getApprovedBy());
            history.setAddedBy(bloodGroupMaster.getAddedBy());
            history.setUpdatedBy(bloodGroupMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            bloodGroupHistoryDAO.save(history);

            logger.info("Out of addBloodGroup");
            return true;
        } catch (Exception e) {
            logger.error("Error in addBloodGroup: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateBloodGroup(BloodGroupMaster bloodGroupMaster) {
        try {
            logger.info("In updateBloodGroup");

            BloodGroupMaster existing = bloodGroupDAO.findById(bloodGroupMaster.getBloodGroupId())
                    .orElseThrow(() -> new RuntimeException("Blood Group not found"));

            LocalDateTime now = LocalDateTime.now();

            BloodGroupHistoryMaster history = new BloodGroupHistoryMaster();
            history.setBloodGroupId(existing.getBloodGroupId());
            history.setBloodGroupName(existing.getBloodGroupName());
            history.setBloodDescription(existing.getBloodDescription());
            history.setIsApproved(bloodGroupMaster.getIsApproved());
            history.setApprovedBy(bloodGroupMaster.getApprovedBy());
            history.setAddedBy(bloodGroupMaster.getAddedBy());
            history.setUpdatedBy(bloodGroupMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setBloodGroupName(bloodGroupMaster.getBloodGroupName());
            existing.setBloodDescription(bloodGroupMaster.getBloodDescription());
            existing.setIsApproved(bloodGroupMaster.getIsApproved());
            existing.setApprovedBy(bloodGroupMaster.getApprovedBy());
            existing.setAddedBy(bloodGroupMaster.getAddedBy());
            existing.setUpdatedBy(bloodGroupMaster.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            bloodGroupDAO.save(existing);
            bloodGroupHistoryDAO.save(history);

            logger.info("Out of updateBloodGroup");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateBloodGroup: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteBloodGroup(BloodGroupMaster bloodGroupMaster) {
        try {
            logger.info("In deleteBloodGroup");

            BloodGroupMaster existing = bloodGroupDAO.findById(bloodGroupMaster.getBloodGroupId())
                    .orElseThrow(() -> new RuntimeException("Blood Group not found"));

            BloodGroupHistoryMaster history = new BloodGroupHistoryMaster();
            history.setBloodGroupId(existing.getBloodGroupId());
            history.setBloodGroupName(existing.getBloodGroupName());
            history.setBloodDescription(existing.getBloodDescription());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            bloodGroupDAO.delete(existing);
            bloodGroupHistoryDAO.save(history);

            logger.info("Out of deleteBloodGroup");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteBloodGroup: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public BloodGroupMaster getBloodGroup(Integer bloodGroupId) {
        try {
            logger.info("In getBloodGroup");
            BloodGroupMaster bloodGroupMaster = bloodGroupDAO.findById(bloodGroupId).orElse(null);
            logger.info("Out of getBloodGroup");
            return bloodGroupMaster;
        } catch (Exception e) {
            logger.error("Error in getBloodGroup: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<BloodGroupMaster> getAllBloodGroups() {
        List<BloodGroupMaster> allBloodGroups = new ArrayList<>();
        try {
            logger.info("In getAllBloodGroups");
            allBloodGroups = (List<BloodGroupMaster>) bloodGroupDAO.findAll();
            logger.info("Out of getAllBloodGroups");
        } catch (Exception e) {
            logger.error("Error in getAllBloodGroups: " + e.getMessage(), e);
        }
        return allBloodGroups;
    }
}
