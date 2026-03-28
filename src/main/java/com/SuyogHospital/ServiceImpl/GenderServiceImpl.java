package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.GenderHistoryMaster;
import com.SuyogHospital.Master.GenderMaster;
import com.SuyogHospital.Repository.GenderDAO;
import com.SuyogHospital.Repository.GenderHistoryDAO;
import com.SuyogHospital.Service.GenderService;

@Service
public class GenderServiceImpl implements GenderService {

    private static final Logger logger = LogManager.getLogger(GenderServiceImpl.class);

    @Autowired
    private GenderDAO genderDAO;

    @Autowired
    private GenderHistoryDAO genderHistoryDAO;

    @Override
    public Boolean addGender(GenderMaster genderMaster) {
        try {
            logger.info("In addGender");

            LocalDateTime now = LocalDateTime.now();
            genderMaster.setDate(now.toLocalDate());
            genderMaster.setTime(now.toLocalTime());

            genderDAO.save(genderMaster);

            GenderHistoryMaster history = new GenderHistoryMaster();
            history.setGenderId(genderMaster.getGenderId());
            history.setGenderType(genderMaster.getGenderType());
            history.setGenderDescription(genderMaster.getGenderDescription());
            history.setIsApproved(genderMaster.getIsApproved());
            history.setApprovedBy(genderMaster.getApprovedBy());
            history.setAddedBy(genderMaster.getAddedBy());
            history.setUpdatedBy(genderMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            genderHistoryDAO.save(history);

            logger.info("Out of addGender");
            return true;
        } catch (Exception e) {
            logger.error("Error in addGender: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateGender(GenderMaster genderMaster) {
        try {
            logger.info("In updateGender");

            GenderMaster existing = genderDAO.findById(genderMaster.getGenderId())
                    .orElseThrow(() -> new RuntimeException("Gender not found"));

            LocalDateTime now = LocalDateTime.now();

            GenderHistoryMaster history = new GenderHistoryMaster();
            history.setGenderId(existing.getGenderId());
            history.setGenderType(existing.getGenderType());
            history.setGenderDescription(existing.getGenderDescription());
            history.setIsApproved(genderMaster.getIsApproved());
            history.setApprovedBy(genderMaster.getApprovedBy());
            history.setAddedBy(genderMaster.getAddedBy());
            history.setUpdatedBy(genderMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setGenderType(genderMaster.getGenderType());
            existing.setGenderDescription(genderMaster.getGenderDescription());
            existing.setIsApproved(genderMaster.getIsApproved());
            existing.setApprovedBy(genderMaster.getApprovedBy());
            existing.setAddedBy(genderMaster.getAddedBy());
            existing.setUpdatedBy(genderMaster.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            genderDAO.save(existing);
            genderHistoryDAO.save(history);
            logger.info("Out of updateGender");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateGender: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteGender(GenderMaster genderMaster) {
        try {
            logger.info("In deleteGender");

            GenderMaster existing = genderDAO.findById(genderMaster.getGenderId())
                    .orElseThrow(() -> new RuntimeException("Gender not found"));

            GenderHistoryMaster history = new GenderHistoryMaster();
            history.setGenderId(existing.getGenderId());
            history.setGenderType(existing.getGenderType());
            history.setGenderDescription(existing.getGenderDescription());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            genderDAO.delete(existing);
            genderHistoryDAO.save(history);
            logger.info("Out of deleteGender");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteGender: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public GenderMaster getGender(Integer genderId) {
        try {
            logger.info("In getGender");
            GenderMaster genderMaster = genderDAO.findById(genderId).orElse(null);
            logger.info("Out of getGender");
            return genderMaster;
        } catch (Exception e) {
            logger.error("Error in getGender: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<GenderMaster> getAllGenders() {
        List<GenderMaster> allGenders = new ArrayList<>();
        try {
            logger.info("In getAllGenders");
            allGenders = (List<GenderMaster>) genderDAO.findAll();
            logger.info("Out of getAllGenders");
        } catch (Exception e) {
            logger.error("Error in getAllGenders: " + e.getMessage(), e);
        }
        return allGenders;
    }
}
