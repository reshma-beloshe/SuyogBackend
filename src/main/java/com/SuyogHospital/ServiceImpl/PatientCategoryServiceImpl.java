package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.PatientCategoryHistoryMaster;
import com.SuyogHospital.Master.PatientCategoryMaster;
import com.SuyogHospital.Repository.PatientCategoryDAO;
import com.SuyogHospital.Repository.PatientCategoryHistoryDAO;
import com.SuyogHospital.Service.PatientCategoryService;

@Service
public class PatientCategoryServiceImpl implements PatientCategoryService {

    private static final Logger logger = LogManager.getLogger(PatientCategoryServiceImpl.class);

    @Autowired
    private PatientCategoryDAO patientCategoryDAO;

    @Autowired
    private PatientCategoryHistoryDAO patientCategoryHistoryDAO;

    @Override
    public Boolean addPatientCategoryService(PatientCategoryMaster patientCategoryMaster) {
        try {
            logger.info("In addPatientCategoryService");

            LocalDateTime now = LocalDateTime.now();
            patientCategoryMaster.setDate(now.toLocalDate());
            patientCategoryMaster.setTime(now.toLocalTime());
            patientCategoryDAO.save(patientCategoryMaster);

            PatientCategoryHistoryMaster history = new PatientCategoryHistoryMaster();
            history.setPatientCategoryId(patientCategoryMaster.getPatientCategoryId());
            history.setPatientCategoryType(patientCategoryMaster.getPatientCategoryType());
            history.setPatientCategoryDescription(patientCategoryMaster.getPatientCategoryDescription());
            history.setStatus(patientCategoryMaster.getStatus());
            history.setIsApproved(patientCategoryMaster.getIsApproved());
            history.setApprovedBy(patientCategoryMaster.getApprovedBy());
            history.setIsIndigent(patientCategoryMaster.getIsIndigent());
            history.setIsWeaker(patientCategoryMaster.getIsWeaker());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            patientCategoryHistoryDAO.save(history);
            logger.info("Out of addPatientCategoryService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updatePatientCategoryService(PatientCategoryMaster patientCategoryMaster) {
        try {
            logger.info("In updatePatientCategoryService");

            PatientCategoryMaster existing = patientCategoryDAO.findById(patientCategoryMaster.getPatientCategoryId())
                    .orElseThrow(() -> new RuntimeException("Patient Category not found"));

            LocalDateTime now = LocalDateTime.now();

            PatientCategoryHistoryMaster history = new PatientCategoryHistoryMaster();
            history.setPatientCategoryId(existing.getPatientCategoryId());
            history.setPatientCategoryType(existing.getPatientCategoryType());
            history.setPatientCategoryDescription(existing.getPatientCategoryDescription());
            history.setStatus(existing.getStatus());
            history.setIsApproved(patientCategoryMaster.getIsApproved());
            history.setApprovedBy(patientCategoryMaster.getApprovedBy());
            history.setIsIndigent(patientCategoryMaster.getIsIndigent());
            history.setIsWeaker(patientCategoryMaster.getIsWeaker());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            existing.setPatientCategoryType(patientCategoryMaster.getPatientCategoryType());
            existing.setPatientCategoryDescription(patientCategoryMaster.getPatientCategoryDescription());
            existing.setStatus(patientCategoryMaster.getStatus());
            existing.setIsApproved(patientCategoryMaster.getIsApproved());
            existing.setApprovedBy(patientCategoryMaster.getApprovedBy());
            existing.setIsIndigent(patientCategoryMaster.getIsIndigent());
            existing.setIsWeaker(patientCategoryMaster.getIsWeaker());

            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            patientCategoryDAO.save(existing);
            patientCategoryHistoryDAO.save(history);
            logger.info("Out of updatePatientCategoryService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deletePatientCategoryService(PatientCategoryMaster patientCategoryMaster) {
        try {
            logger.info("In deletePatientCategoryService");

            PatientCategoryMaster existing = patientCategoryDAO.findById(patientCategoryMaster.getPatientCategoryId())
                    .orElseThrow(() -> new RuntimeException("Patient Category not found"));

            PatientCategoryHistoryMaster history = new PatientCategoryHistoryMaster();
            history.setPatientCategoryId(existing.getPatientCategoryId());
            history.setPatientCategoryType(existing.getPatientCategoryType());
            history.setPatientCategoryDescription(existing.getPatientCategoryDescription());
            history.setStatus(existing.getStatus());
            history.setIsApproved(patientCategoryMaster.getIsApproved());
            history.setApprovedBy(patientCategoryMaster.getApprovedBy());
            history.setIsIndigent(patientCategoryMaster.getIsIndigent());
            history.setIsWeaker(patientCategoryMaster.getIsWeaker());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            patientCategoryDAO.delete(existing);
            patientCategoryHistoryDAO.save(history);
            logger.info("Out of deletePatientCategoryService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public PatientCategoryMaster getPatientCategoryService(Integer patientCategoryId) {
        try {
            logger.info("In getPatientCategoryService");
            PatientCategoryMaster patientCategoryMaster = patientCategoryDAO.findById(patientCategoryId).orElse(null);
            logger.info("Out of getPatientCategoryService");
            return patientCategoryMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<PatientCategoryMaster> getAllPatientCategoryService() {
        List<PatientCategoryMaster> allPatientCategories = new ArrayList<>();
        try {
            logger.info("In getAllPatientCategoryService");
            allPatientCategories = (List<PatientCategoryMaster>) patientCategoryDAO.findAll();
            logger.info("Out of getAllPatientCategoryService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allPatientCategories;
    }
}
