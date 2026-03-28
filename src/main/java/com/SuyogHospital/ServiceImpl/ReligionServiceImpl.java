package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.ReligionHistoryMaster;
import com.SuyogHospital.Master.ReligionMaster;
import com.SuyogHospital.Repository.ReligionDAO;
import com.SuyogHospital.Repository.ReligionHistoryDAO;
import com.SuyogHospital.Service.ReligionService;

@Service
public class ReligionServiceImpl implements ReligionService {

    private static final Logger logger = LogManager.getLogger(ReligionServiceImpl.class);

    @Autowired
    private ReligionDAO religionDAO;

    @Autowired
    private ReligionHistoryDAO religionHistoryDAO;

    @Override
    public Boolean addReligion(ReligionMaster religionMaster) {
        try {
            logger.info("In addReligion");

            LocalDateTime now = LocalDateTime.now();
            religionMaster.setDate(now.toLocalDate());
            religionMaster.setTime(now.toLocalTime());

            religionDAO.save(religionMaster);

            ReligionHistoryMaster history = new ReligionHistoryMaster();
            history.setReligionId(religionMaster.getReligionId());
            history.setReligiionName(religionMaster.getReligiionName());
            history.setReligiionDescription(religionMaster.getReligiionDescription());
            history.setIsApproved(religionMaster.getIsApproved());
            history.setApprovedBy(religionMaster.getApprovedBy());
            history.setAddedBy(religionMaster.getAddedBy());
            history.setUpdatedBy(religionMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            religionHistoryDAO.save(history);

            logger.info("Out of addReligion");
            return true;
        } catch (Exception e) {
            logger.error("Error in addReligion: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateReligion(ReligionMaster religionMaster) {
        try {
            logger.info("In updateReligion");

            ReligionMaster existing = religionDAO.findById(religionMaster.getReligionId())
                    .orElseThrow(() -> new RuntimeException("Religion not found"));

            LocalDateTime now = LocalDateTime.now();

            ReligionHistoryMaster history = new ReligionHistoryMaster();
            history.setReligionId(existing.getReligionId());
            history.setReligiionName(existing.getReligiionName());
            history.setReligiionDescription(existing.getReligiionDescription());
            history.setIsApproved(religionMaster.getIsApproved());
            history.setApprovedBy(religionMaster.getApprovedBy());
            history.setAddedBy(religionMaster.getAddedBy());
            history.setUpdatedBy(religionMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setReligiionName(religionMaster.getReligiionName());
            existing.setReligiionDescription(religionMaster.getReligiionDescription());
            existing.setIsApproved(religionMaster.getIsApproved());
            existing.setApprovedBy(religionMaster.getApprovedBy());
            existing.setAddedBy(religionMaster.getAddedBy());
            existing.setUpdatedBy(religionMaster.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            religionDAO.save(existing);
            religionHistoryDAO.save(history);

            logger.info("Out of updateReligion");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateReligion: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteReligion(ReligionMaster religionMaster) {
        try {
            logger.info("In deleteReligion");

            ReligionMaster existing = religionDAO.findById(religionMaster.getReligionId())
                    .orElseThrow(() -> new RuntimeException("Religion not found"));

            ReligionHistoryMaster history = new ReligionHistoryMaster();
            history.setReligionId(existing.getReligionId());
            history.setReligiionName(existing.getReligiionName());
            history.setReligiionDescription(existing.getReligiionDescription());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            religionDAO.delete(existing);
            religionHistoryDAO.save(history);

            logger.info("Out of deleteReligion");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteReligion: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public ReligionMaster getReligion(Integer religionId) {
        try {
            logger.info("In getReligion");
            ReligionMaster religionMaster = religionDAO.findById(religionId).orElse(null);
            logger.info("Out of getReligion");
            return religionMaster;
        } catch (Exception e) {
            logger.error("Error in getReligion: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<ReligionMaster> getAllReligions() {
        List<ReligionMaster> allReligions = new ArrayList<>();
        try {
            logger.info("In getAllReligions");
            allReligions = (List<ReligionMaster>) religionDAO.findAll();
            logger.info("Out of getAllReligions");
        } catch (Exception e) {
            logger.error("Error in getAllReligions: " + e.getMessage(), e);
        }
        return allReligions;
    }
}
