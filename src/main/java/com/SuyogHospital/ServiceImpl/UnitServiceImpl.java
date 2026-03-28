package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.UnitHistoryMaster;
import com.SuyogHospital.Master.UnitMaster;
import com.SuyogHospital.Repository.UnitDAO;
import com.SuyogHospital.Repository.UnitHistoryDAO;
import com.SuyogHospital.Service.UnitService;

@Service
public class UnitServiceImpl implements UnitService {

    private static final Logger logger = LogManager.getLogger(UnitServiceImpl.class);

    @Autowired
    private UnitDAO unitDAO;

    @Autowired
    private UnitHistoryDAO unitHistoryDAO;

    @Override
    public Boolean addUnitService(UnitMaster unitMaster) {
        try {
            logger.info("In addUnitService");

            // Set current date/time for created
            LocalDateTime now = LocalDateTime.now();
            unitMaster.setCreatedAt(now);
            unitMaster.setUpdatedAt(now);
            unitMaster.setApplicableFrom(now);

            unitDAO.save(unitMaster);

            UnitHistoryMaster history = new UnitHistoryMaster();
            history.setUnitId(unitMaster.getUnitId());
            history.setUnitName(unitMaster.getUnitName());
            history.setDescription(unitMaster.getDescription());
            history.setAbbreviation(unitMaster.getAbbreviation());
            history.setOpdNoPrefix(unitMaster.getOpdNoPrefix());
            history.setIpdNoPrefix(unitMaster.getIpdNoPrefix());
            history.setStatus(unitMaster.getStatus());
            history.setApprovedBy(unitMaster.getApprovedBy());
            history.setAddedBy(unitMaster.getAddedBy());
            history.setUpdatedBy(unitMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            unitHistoryDAO.save(history);
            logger.info("Out of addUnitService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateUnitService(UnitMaster unitMaster) {
        try {
            logger.info("In updateUnitService");

            UnitMaster existing = unitDAO.findById(unitMaster.getUnitId())
                    .orElseThrow(() -> new RuntimeException("Unit not found"));

            LocalDateTime now = LocalDateTime.now();

            UnitHistoryMaster history = new UnitHistoryMaster();
            history.setUnitId(existing.getUnitId());
            history.setUnitName(existing.getUnitName());
            history.setDescription(existing.getDescription());
            history.setAbbreviation(existing.getAbbreviation());
            history.setOpdNoPrefix(existing.getOpdNoPrefix());
            history.setIpdNoPrefix(existing.getIpdNoPrefix());
            history.setStatus(existing.getStatus());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            // Update values
            existing.setUnitName(unitMaster.getUnitName());
            existing.setDescription(unitMaster.getDescription());
            existing.setAbbreviation(unitMaster.getAbbreviation());
            existing.setOpdNoPrefix(unitMaster.getOpdNoPrefix());
            existing.setIpdNoPrefix(unitMaster.getIpdNoPrefix());
            existing.setStatus(unitMaster.getStatus());
            existing.setApprovedBy(unitMaster.getApprovedBy());
            existing.setAddedBy(unitMaster.getAddedBy());
            existing.setUpdatedBy(unitMaster.getUpdatedBy());

            unitDAO.save(existing);
            unitHistoryDAO.save(history);
            logger.info("Out of updateUnitService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteUnitService(UnitMaster unitMaster) {
        try {
            logger.info("In deleteUnitService");

            UnitMaster existing = unitDAO.findById(unitMaster.getUnitId())
                    .orElseThrow(() -> new RuntimeException("Unit not found"));

            UnitHistoryMaster history = new UnitHistoryMaster();
            history.setUnitId(existing.getUnitId());
            history.setUnitName(existing.getUnitName());
            history.setDescription(existing.getDescription());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            unitDAO.delete(existing);
            unitHistoryDAO.save(history);
            logger.info("Out of deleteUnitService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public UnitMaster getUnitService(Integer unitId) {
        try {
            logger.info("In getUnitService");
            UnitMaster unitMaster = unitDAO.findById(unitId).orElse(null);
            logger.info("Out of getUnitService");
            return unitMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<UnitMaster> getAllUnitService() {
        List<UnitMaster> allUnits = new ArrayList<>();
        try {
            logger.info("In getAllUnitService");
            allUnits = (List<UnitMaster>) unitDAO.findAll();
            logger.info("Out of getAllUnitService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allUnits;
    }
}
