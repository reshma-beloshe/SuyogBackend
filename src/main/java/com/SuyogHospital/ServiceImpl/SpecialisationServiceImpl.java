package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.SpecialisationHistoryMaster;
import com.SuyogHospital.Master.SpecialisationMaster;
import com.SuyogHospital.Repository.SpecialisationDAO;
import com.SuyogHospital.Repository.SpecialisationHistoryDAO;
import com.SuyogHospital.Service.SpecialisationService;

@Service
public class SpecialisationServiceImpl implements SpecialisationService {

    private static final Logger logger = LogManager.getLogger(SpecialisationServiceImpl.class);

    @Autowired
    private SpecialisationDAO specialisationDAO;

    @Autowired
    private SpecialisationHistoryDAO specialisationHistoryDAO;

    @Override
    public Boolean addSpecialisationService(SpecialisationMaster specialisationMaster) {
        try {
            logger.info("In addSpecialisationService");

            LocalDateTime now = LocalDateTime.now();
            specialisationMaster.setDate(now.toLocalDate());
            specialisationMaster.setTime(now.toLocalTime());
            specialisationDAO.save(specialisationMaster);

            SpecialisationHistoryMaster history = new SpecialisationHistoryMaster();
            history.setSpecialisationId(specialisationMaster.getSpecialisationId());
            history.setSpecialisationType(specialisationMaster.getSpecialisationType());
            history.setDescription(specialisationMaster.getDescription());
            history.setStatus(specialisationMaster.getStatus());
            history.setIsApproved(specialisationMaster.getIsApproved());
            history.setApprovedBy(specialisationMaster.getApprovedBy());
            history.setAddedBy(specialisationMaster.getAddedBy());
            history.setUpdatedBy(specialisationMaster.getUpdatedBy());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            specialisationHistoryDAO.save(history);
            logger.info("Out of addSpecialisationService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateSpecialisationService(SpecialisationMaster specialisationMaster) {
        try {
            logger.info("In updateSpecialisationService");

            SpecialisationMaster existing = specialisationDAO.findById(specialisationMaster.getSpecialisationId())
                    .orElseThrow(() -> new RuntimeException("Specialisation not found"));

            LocalDateTime now = LocalDateTime.now();

            SpecialisationHistoryMaster history = new SpecialisationHistoryMaster();
            history.setSpecialisationId(existing.getSpecialisationId());
            history.setSpecialisationType(existing.getSpecialisationType());
            history.setDescription(existing.getDescription());
            history.setStatus(existing.getStatus());
            history.setApplicableFrom(existing.getApplicableFrom());
            history.setIsApproved(specialisationMaster.getIsApproved());
            history.setApprovedBy(specialisationMaster.getApprovedBy());
            history.setAddedBy(specialisationMaster.getAddedBy());
            history.setUpdatedBy(specialisationMaster.getUpdatedBy());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            // Update values
            existing.setSpecialisationType(specialisationMaster.getSpecialisationType());
            existing.setDescription(specialisationMaster.getDescription());
            existing.setStatus(specialisationMaster.getStatus());
            existing.setApplicableFrom(specialisationMaster.getApplicableFrom());
            existing.setIsApproved(specialisationMaster.getIsApproved());
            existing.setApprovedBy(specialisationMaster.getApprovedBy());
            existing.setAddedBy(specialisationMaster.getAddedBy());
            existing.setUpdatedBy(specialisationMaster.getUpdatedBy());

            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            specialisationDAO.save(existing);
            specialisationHistoryDAO.save(history);
            logger.info("Out of updateSpecialisationService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteSpecialisationService(SpecialisationMaster specialisationMaster) {
        try {
            logger.info("In deleteSpecialisationService");

            SpecialisationMaster existing = specialisationDAO.findById(specialisationMaster.getSpecialisationId())
                    .orElseThrow(() -> new RuntimeException("Specialisation not found"));

            SpecialisationHistoryMaster history = new SpecialisationHistoryMaster();
            history.setSpecialisationId(existing.getSpecialisationId());
            history.setSpecialisationType(existing.getSpecialisationType());
            history.setDescription(existing.getDescription());
            history.setStatus(existing.getStatus());
            history.setApplicableFrom(existing.getApplicableFrom());
            history.setIsApproved(specialisationMaster.getIsApproved());
            history.setApprovedBy(specialisationMaster.getApprovedBy());
            history.setAddedBy(specialisationMaster.getAddedBy());
            history.setUpdatedBy(specialisationMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            specialisationDAO.delete(existing);
            specialisationHistoryDAO.save(history);
            logger.info("Out of deleteSpecialisationService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public SpecialisationMaster getSpecialisationService(Integer specialisationId) {
        try {
            logger.info("In getSpecialisationService");
            SpecialisationMaster specialisationMaster = specialisationDAO.findById(specialisationId).orElse(null);
            logger.info("Out of getSpecialisationService");
            return specialisationMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<SpecialisationMaster> getAllSpecialisationService() {
        List<SpecialisationMaster> allSpecialisations = new ArrayList<>();
        try {
            logger.info("In getAllSpecialisationService");
            allSpecialisations = (List<SpecialisationMaster>) specialisationDAO.findAll();
            logger.info("Out of getAllSpecialisationService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allSpecialisations;
    }
}
	