package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.DoctorTypeHistoryMaster;
import com.SuyogHospital.Master.DoctorTypeMaster;
import com.SuyogHospital.Repository.DoctorTypeDAO;
import com.SuyogHospital.Repository.DoctorTypeHistoryDAO;
import com.SuyogHospital.Service.DoctorTypeService;

@Service
public class DoctorTypeServiceImpl implements DoctorTypeService {

    private static final Logger logger = LogManager.getLogger(DoctorTypeServiceImpl.class);

    @Autowired
    private DoctorTypeDAO doctorTypeDAO;

    @Autowired
    private DoctorTypeHistoryDAO doctorTypeHistoryDAO;

    @Override
    public Boolean addDoctorTypeService(DoctorTypeMaster doctorTypeMaster) {
        try {
            logger.info("In addDoctorTypeService");

            LocalDateTime now = LocalDateTime.now();
            doctorTypeMaster.setDate(now.toLocalDate());
            doctorTypeMaster.setTime(now.toLocalTime());

            doctorTypeDAO.save(doctorTypeMaster);

            DoctorTypeHistoryMaster history = new DoctorTypeHistoryMaster();
            history.setDoctorTypeId(doctorTypeMaster.getDoctorTypeId());
            history.setDoctorTypeName(doctorTypeMaster.getDoctorTypeName());
            history.setDescription(doctorTypeMaster.getDescription());
            history.setAddedBy(doctorTypeMaster.getAddedBy());
            history.setUpdatedBy(doctorTypeMaster.getUpdatedBy());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            doctorTypeHistoryDAO.save(history);
            logger.info("Out of addDoctorTypeService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateDoctorTypeService(DoctorTypeMaster doctorTypeMaster) {
        try {
            logger.info("In updateDoctorTypeService");

            DoctorTypeMaster existing = doctorTypeDAO.findById(doctorTypeMaster.getDoctorTypeId())
                    .orElseThrow(() -> new RuntimeException("Doctor type not found"));

            LocalDateTime now = LocalDateTime.now();

            DoctorTypeHistoryMaster history = new DoctorTypeHistoryMaster();
            history.setDoctorTypeId(existing.getDoctorTypeId());
            history.setDoctorTypeName(existing.getDoctorTypeName());
            history.setDescription(existing.getDescription());
            history.setAddedBy(doctorTypeMaster.getAddedBy());
            history.setUpdatedBy(doctorTypeMaster.getUpdatedBy());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            existing.setDoctorTypeName(doctorTypeMaster.getDoctorTypeName());
            existing.setDescription(doctorTypeMaster.getDescription());
            existing.setAddedBy(doctorTypeMaster.getAddedBy());
            existing.setUpdatedBy(doctorTypeMaster.getUpdatedBy());

            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            doctorTypeDAO.save(existing);
            doctorTypeHistoryDAO.save(history);
            logger.info("Out of updateDoctorTypeService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteDoctorTypeService(DoctorTypeMaster doctorTypeMaster) {
        try {
            logger.info("In deleteDoctorTypeService");
            DoctorTypeMaster existing = doctorTypeDAO.findById(doctorTypeMaster.getDoctorTypeId())
                    .orElseThrow(() -> new RuntimeException("Doctor type not found"));

            DoctorTypeHistoryMaster history = new DoctorTypeHistoryMaster();
            history.setDoctorTypeId(existing.getDoctorTypeId());
            history.setDoctorTypeName(existing.getDoctorTypeName());
            history.setDescription(existing.getDescription());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            doctorTypeDAO.delete(existing);
            doctorTypeHistoryDAO.save(history);
            logger.info("Out of deleteDoctorTypeService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public DoctorTypeMaster getDoctorTypeService(Integer doctorTypeId) {
        try {
            logger.info("In getDoctorTypeService");
            DoctorTypeMaster doctorTypeMaster = doctorTypeDAO.findById(doctorTypeId).orElse(null);
            logger.info("Out of getDoctorTypeService");
            return doctorTypeMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<DoctorTypeMaster> getAllDoctorTypeService() {
        List<DoctorTypeMaster> allDoctorTypes = new ArrayList<>();
        try {
            logger.info("In getAllDoctorTypeService");
            allDoctorTypes = (List<DoctorTypeMaster>) doctorTypeDAO.findAll();
            logger.info("Out of getAllDoctorTypeService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allDoctorTypes;
    }
}
