package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.DistrictMaster;
import com.SuyogHospital.Master.TalukaHistoryMaster;
import com.SuyogHospital.Master.TalukaMaster;
import com.SuyogHospital.Repository.TalukaDAO;
import com.SuyogHospital.Repository.TalukaHistoryDAO;
import com.SuyogHospital.Service.DistrictService;
import com.SuyogHospital.Service.TalukaService;

@Service
public class TalukaServiceImpl implements TalukaService {

    private static final Logger logger = LogManager.getLogger(TalukaServiceImpl.class);

    @Autowired
    private TalukaDAO talukaDAO;

    @Autowired
    private TalukaHistoryDAO talHistoryDAO;
    
    @Autowired
    private DistrictService districtService;

    @Override
    public Boolean addTalukaService(TalukaMaster TalukaMaster) {
        try {
            logger.info("In addTalukaService");
            LocalDateTime now = LocalDateTime.now();
            TalukaMaster.setDate(now.toLocalDate());
            TalukaMaster.setTime(now.toLocalTime());

            // Validate and set District
            DistrictMaster district = districtService.getDistrictService(TalukaMaster.getDistrict().getDistrictId());
            if (district != null) {
                TalukaMaster.setDistrict(district);
            } else {
                logger.error("Invalid district ID");
                return false;
            }

            talukaDAO.save(TalukaMaster);

            TalukaHistoryMaster history = new TalukaHistoryMaster();
            history.setTalId(TalukaMaster.getTalId());
            history.setTalName(TalukaMaster.getTalName());
            history.setTalDesc(TalukaMaster.getTalDesc());
            history.setDistrictId(TalukaMaster.getDistrict().getDistrictId());
            history.setIsApproved(TalukaMaster.getIsApproved());
            history.setApprovedBy(TalukaMaster.getApprovedBy());
            history.setAddedBy(TalukaMaster.getAddedBy());
            history.setUpdatedBy(TalukaMaster.getUpdatedBy());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            talHistoryDAO.save(history);
            logger.info("Out of addTalukaService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateTalukaService(TalukaMaster TalukaMaster) {
        try {
            logger.info("In updateTalukaService");

            TalukaMaster existing = talukaDAO.findById(TalukaMaster.getTalId())
                    .orElseThrow(() -> new RuntimeException("Taluka not found"));

            LocalDateTime now = LocalDateTime.now();

            TalukaHistoryMaster history = new TalukaHistoryMaster();
            history.setTalId(existing.getTalId());
            history.setTalName(existing.getTalName());
            history.setTalDesc(existing.getTalDesc());
            history.setDistrictId(TalukaMaster.getDistrict().getDistrictId());
            history.setIsApproved(TalukaMaster.getIsApproved());
            history.setApprovedBy(TalukaMaster.getApprovedBy());
            history.setAddedBy(TalukaMaster.getAddedBy());
            history.setUpdatedBy(TalukaMaster.getUpdatedBy());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            existing.setTalName(TalukaMaster.getTalName());
            existing.setTalDesc(TalukaMaster.getTalDesc());
            existing.setIsApproved(TalukaMaster.getIsApproved());
            existing.setApprovedBy(TalukaMaster.getApprovedBy());
            existing.setAddedBy(TalukaMaster.getAddedBy());
            existing.setUpdatedBy(TalukaMaster.getUpdatedBy());

            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            talukaDAO.save(existing);
            talHistoryDAO.save(history);
            logger.info("Out of updateTalukaService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteTalukaService(TalukaMaster TalukaMaster) {
        try {
            logger.info("In deleteTalukaService");
            TalukaMaster existing = talukaDAO.findById(TalukaMaster.getTalId())
                    .orElseThrow(() -> new RuntimeException("Taluka not found"));

            TalukaHistoryMaster history = new TalukaHistoryMaster();
            history.setTalId(existing.getTalId());
            history.setTalName(existing.getTalName());
            history.setTalDesc(existing.getTalDesc());
            history.setDistrictId(TalukaMaster.getDistrict().getDistrictId());
            history.setIsApproved(TalukaMaster.getIsApproved());
            history.setApprovedBy(TalukaMaster.getApprovedBy());
            history.setAddedBy(TalukaMaster.getAddedBy());
            history.setUpdatedBy(TalukaMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            talukaDAO.delete(existing);
            talHistoryDAO.save(history);
            logger.info("Out of deleteTalukaService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public TalukaMaster getTalukaService(Integer talId) {
        try {
            logger.info("In getTalukaService");
            TalukaMaster TalukaMaster = talukaDAO.findById(talId).orElse(null);
            logger.info("Out of getTalukaService");
            return TalukaMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<TalukaMaster> getAllTalukaService() {
        List<TalukaMaster> allTalukas = new ArrayList<>();
        try {
            logger.info("In getAllTalukaService");
            allTalukas = (List<TalukaMaster>) talukaDAO.findAll();
            logger.info("Out of getAllTalukaService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allTalukas;
    }
}
