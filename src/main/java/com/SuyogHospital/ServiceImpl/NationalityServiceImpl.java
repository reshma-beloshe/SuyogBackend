package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.NationalityHistoryMaster;
import com.SuyogHospital.Master.NationalityMaster;
import com.SuyogHospital.Repository.NationalityDAO;
import com.SuyogHospital.Repository.NationalityHistoryDAO;
import com.SuyogHospital.Service.NationalityService;

@Service
public class NationalityServiceImpl implements NationalityService {

    private static final Logger logger = LogManager.getLogger(NationalityServiceImpl.class);

    @Autowired
    private NationalityDAO nationalityDAO;

    @Autowired
    private NationalityHistoryDAO nationalityHistoryDAO;

    @Override
    public Boolean addNationality(NationalityMaster nationalityMaster) {
        try {
            logger.info("In addNationality");

            LocalDateTime now = LocalDateTime.now();
            nationalityMaster.setDate(now.toLocalDate());
            nationalityMaster.setTime(now.toLocalTime());

            nationalityDAO.save(nationalityMaster);

            NationalityHistoryMaster history = new NationalityHistoryMaster();
            history.setNationalityId(nationalityMaster.getNationalityId());
            history.setNationalityName(nationalityMaster.getNationalityName());
            history.setNationalityDescription(nationalityMaster.getNationalityDescription());
            history.setIsApproved(nationalityMaster.getIsApproved());
            history.setApprovedBy(nationalityMaster.getApprovedBy());
            history.setAddedBy(nationalityMaster.getAddedBy());
            history.setUpdatedBy(nationalityMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            nationalityHistoryDAO.save(history);

            logger.info("Out of addNationality");
            return true;
        } catch (Exception e) {
            logger.error("Error in addNationality: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateNationality(NationalityMaster nationalityMaster) {
        try {
            logger.info("In updateNationality");

            NationalityMaster existing = nationalityDAO.findById(nationalityMaster.getNationalityId())
                    .orElseThrow(() -> new RuntimeException("Nationality not found"));

            LocalDateTime now = LocalDateTime.now();

            NationalityHistoryMaster history = new NationalityHistoryMaster();
            history.setNationalityId(existing.getNationalityId());
            history.setNationalityName(existing.getNationalityName());
            history.setNationalityDescription(existing.getNationalityDescription());
            history.setIsApproved(nationalityMaster.getIsApproved());
            history.setApprovedBy(nationalityMaster.getApprovedBy());
            history.setAddedBy(nationalityMaster.getAddedBy());
            history.setUpdatedBy(nationalityMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setNationalityName(nationalityMaster.getNationalityName());
            existing.setNationalityDescription(nationalityMaster.getNationalityDescription());
            existing.setIsApproved(nationalityMaster.getIsApproved());
            existing.setApprovedBy(nationalityMaster.getApprovedBy());
            existing.setAddedBy(nationalityMaster.getAddedBy());
            existing.setUpdatedBy(nationalityMaster.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            nationalityDAO.save(existing);
            nationalityHistoryDAO.save(history);

            logger.info("Out of updateNationality");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateNationality: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteNationality(NationalityMaster nationalityMaster) {
        try {
            logger.info("In deleteNationality");

            NationalityMaster existing = nationalityDAO.findById(nationalityMaster.getNationalityId())
                    .orElseThrow(() -> new RuntimeException("Nationality not found"));

            NationalityHistoryMaster history = new NationalityHistoryMaster();
            history.setNationalityId(existing.getNationalityId());
            history.setNationalityName(existing.getNationalityName());
            history.setNationalityDescription(existing.getNationalityDescription());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            nationalityDAO.delete(existing);
            nationalityHistoryDAO.save(history);

            logger.info("Out of deleteNationality");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteNationality: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public NationalityMaster getNationality(Integer nationalityId) {
        try {
            logger.info("In getNationality");
            NationalityMaster nationalityMaster = nationalityDAO.findById(nationalityId).orElse(null);
            logger.info("Out of getNationality");
            return nationalityMaster;
        } catch (Exception e) {
            logger.error("Error in getNationality: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<NationalityMaster> getAllNationalities() {
        List<NationalityMaster> allNationalities = new ArrayList<>();
        try {
            logger.info("In getAllNationalities");
            allNationalities = (List<NationalityMaster>) nationalityDAO.findAll();
            logger.info("Out of getAllNationalities");
        } catch (Exception e) {
            logger.error("Error in getAllNationalities: " + e.getMessage(), e);
        }
        return allNationalities;
    }
}
