package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.ProcedureTypeHistoryMaster;
import com.SuyogHospital.Master.ProcedureTypeMaster;
import com.SuyogHospital.Repository.ProcedureTypeDAO;
import com.SuyogHospital.Repository.ProcedureTypeHistoryDAO;
import com.SuyogHospital.Service.ProcedureTypeService;

@Service
public class ProcedureTypeServiceImpl implements ProcedureTypeService {

    private static final Logger logger = LogManager.getLogger(ProcedureTypeServiceImpl.class);

    @Autowired
    private ProcedureTypeDAO procedureTypeDAO;

    @Autowired
    private ProcedureTypeHistoryDAO procedureTypeHistoryDAO;

    @Override
    public Boolean addProcedureType(ProcedureTypeMaster procedureTypeMaster) {
        try {
            logger.info("In addProcedureType");

            LocalDateTime now = LocalDateTime.now();
            procedureTypeMaster.setDate(now.toLocalDate());
            procedureTypeMaster.setTime(now.toLocalTime());

            procedureTypeDAO.save(procedureTypeMaster);

            ProcedureTypeHistoryMaster history = new ProcedureTypeHistoryMaster();
            history.setProcedureTypeId(procedureTypeMaster.getProcedureTypeId());
            history.setProcedureTypeName(procedureTypeMaster.getProcedureTypeName());
            history.setProcedureTypeDescription(procedureTypeMaster.getProcedureTypeDescription());
            history.setProcedureTypeStatus(procedureTypeMaster.getProcedureTypeStatus());
            history.setIdSurgical(procedureTypeMaster.getIdSurgical());
            history.setIsApproved(procedureTypeMaster.getIsApproved());
            history.setApprovedBy(procedureTypeMaster.getApprovedBy());
            history.setAddedBy(procedureTypeMaster.getAddedBy());
            history.setUpdatedBy(procedureTypeMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            procedureTypeHistoryDAO.save(history);

            logger.info("Out of addProcedureType");
            return true;
        } catch (Exception e) {
            logger.error("Error in addProcedureType: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateProcedureType(ProcedureTypeMaster procedureTypeMaster) {
        try {
            logger.info("In updateProcedureType");

            ProcedureTypeMaster existing = procedureTypeDAO.findById(procedureTypeMaster.getProcedureTypeId())
                    .orElseThrow(() -> new RuntimeException("Procedure Type not found"));

            LocalDateTime now = LocalDateTime.now();

            ProcedureTypeHistoryMaster history = new ProcedureTypeHistoryMaster();
            history.setProcedureTypeId(existing.getProcedureTypeId());
            history.setProcedureTypeName(existing.getProcedureTypeName());
            history.setProcedureTypeDescription(existing.getProcedureTypeDescription());
            history.setProcedureTypeStatus(existing.getProcedureTypeStatus());
            history.setIdSurgical(existing.getIdSurgical());
            history.setIsApproved(procedureTypeMaster.getIsApproved());
            history.setApprovedBy(procedureTypeMaster.getApprovedBy());
            history.setAddedBy(procedureTypeMaster.getAddedBy());
            history.setUpdatedBy(procedureTypeMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setProcedureTypeName(procedureTypeMaster.getProcedureTypeName());
            existing.setProcedureTypeDescription(procedureTypeMaster.getProcedureTypeDescription());
            existing.setProcedureTypeStatus(procedureTypeMaster.getProcedureTypeStatus());
            existing.setIdSurgical(procedureTypeMaster.getIdSurgical());
            existing.setIsApproved(procedureTypeMaster.getIsApproved());
            existing.setApprovedBy(procedureTypeMaster.getApprovedBy());
            existing.setAddedBy(procedureTypeMaster.getAddedBy());
            existing.setUpdatedBy(procedureTypeMaster.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            procedureTypeDAO.save(existing);
            procedureTypeHistoryDAO.save(history);

            logger.info("Out of updateProcedureType");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateProcedureType: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteProcedureType(ProcedureTypeMaster procedureTypeMaster) {
        try {
            logger.info("In deleteProcedureType");

            ProcedureTypeMaster existing = procedureTypeDAO.findById(procedureTypeMaster.getProcedureTypeId())
                    .orElseThrow(() -> new RuntimeException("Procedure Type not found"));

            ProcedureTypeHistoryMaster history = new ProcedureTypeHistoryMaster();
            history.setProcedureTypeId(existing.getProcedureTypeId());
            history.setProcedureTypeName(existing.getProcedureTypeName());
            history.setProcedureTypeDescription(existing.getProcedureTypeDescription());
            history.setProcedureTypeStatus(existing.getProcedureTypeStatus());
            history.setIdSurgical(existing.getIdSurgical());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            procedureTypeDAO.delete(existing);
            procedureTypeHistoryDAO.save(history);

            logger.info("Out of deleteProcedureType");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteProcedureType: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public ProcedureTypeMaster getProcedureType(Integer procedureTypeId) {
        try {
            logger.info("In getProcedureType");
            ProcedureTypeMaster procedureTypeMaster = procedureTypeDAO.findById(procedureTypeId).orElse(null);
            logger.info("Out of getProcedureType");
            return procedureTypeMaster;
        } catch (Exception e) {
            logger.error("Error in getProcedureType: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<ProcedureTypeMaster> getAllProcedureTypes() {
        List<ProcedureTypeMaster> allProcedureTypes = new ArrayList<>();
        try {
            logger.info("In getAllProcedureTypes");
            allProcedureTypes = (List<ProcedureTypeMaster>) procedureTypeDAO.findAll();
            logger.info("Out of getAllProcedureTypes");
        } catch (Exception e) {
            logger.error("Error in getAllProcedureTypes: " + e.getMessage(), e);
        }
        return allProcedureTypes;
    }
}
