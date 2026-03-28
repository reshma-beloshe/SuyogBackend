package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.DepartmentHistoryMaster;
import com.SuyogHospital.Master.DepartmentMaster;
import com.SuyogHospital.Repository.DepartmentDAO;
import com.SuyogHospital.Repository.DepartmentHistoryDAO;
import com.SuyogHospital.Service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private DepartmentHistoryDAO departmentHistoryDAO;

    @Override
    public Boolean addDepartmentService(DepartmentMaster departmentMaster) {
        try {
            logger.info("In addDepartmentService");

            LocalDateTime now = LocalDateTime.now();
            departmentMaster.setCreatedAt(now);
            departmentMaster.setUpdatedAt(now);

            departmentDAO.save(departmentMaster);

            DepartmentHistoryMaster history = new DepartmentHistoryMaster();
            history.setDepartmentId(departmentMaster.getDepartmentId());
            history.setDepartmentName(departmentMaster.getDepartmentName());
            history.setDescription(departmentMaster.getDescription());
            history.setNameOfHOD(departmentMaster.getNameOfHOD());
            history.setTarrifApplicable(departmentMaster.getTarrifApplicable());
            history.setStatus(departmentMaster.getStatus());
            history.setIsApproved(departmentMaster.getIsApproved());
            history.setApprovedBy(departmentMaster.getApprovedBy());
            history.setAddedBy(departmentMaster.getAddedBy());
            history.setUpdatedBy(departmentMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            departmentHistoryDAO.save(history);
            logger.info("Out of addDepartmentService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateDepartmentService(DepartmentMaster departmentMaster) {
        try {
            logger.info("In updateDepartmentService");

            DepartmentMaster existing = departmentDAO.findById(departmentMaster.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            LocalDateTime now = LocalDateTime.now();

            DepartmentHistoryMaster history = new DepartmentHistoryMaster();
            history.setDepartmentId(existing.getDepartmentId());
            history.setDepartmentName(existing.getDepartmentName());
            history.setDescription(existing.getDescription());
            history.setNameOfHOD(existing.getNameOfHOD());
            history.setTarrifApplicable(existing.getTarrifApplicable());
            history.setStatus(existing.getStatus());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setDepartmentName(departmentMaster.getDepartmentName());
            existing.setDescription(departmentMaster.getDescription());
            existing.setNameOfHOD(departmentMaster.getNameOfHOD());
            existing.setTarrifApplicable(departmentMaster.getTarrifApplicable());
            existing.setStatus(departmentMaster.getStatus());
            existing.setIsApproved(departmentMaster.getIsApproved());
            existing.setApprovedBy(departmentMaster.getApprovedBy());
            existing.setAddedBy(departmentMaster.getAddedBy());
            existing.setUpdatedBy(departmentMaster.getUpdatedBy());

            departmentDAO.save(existing);
            departmentHistoryDAO.save(history);
            logger.info("Out of updateDepartmentService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteDepartmentService(DepartmentMaster departmentMaster) {
        try {
            logger.info("In deleteDepartmentService");

            DepartmentMaster existing = departmentDAO.findById(departmentMaster.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            DepartmentHistoryMaster history = new DepartmentHistoryMaster();
            history.setDepartmentId(existing.getDepartmentId());
            history.setDepartmentName(existing.getDepartmentName());
            history.setDescription(existing.getDescription());
            history.setDescription(existing.getNameOfHOD());
            history.setTarrifApplicable(existing.getTarrifApplicable());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            departmentDAO.delete(existing);
            departmentHistoryDAO.save(history);
            logger.info("Out of deleteDepartmentService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public DepartmentMaster getDepartmentService(Integer departmentId) {
        try {
            logger.info("In getDepartmentService");
            DepartmentMaster departmentMaster = departmentDAO.findById(departmentId).orElse(null);
            logger.info("Out of getDepartmentService");
            return departmentMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<DepartmentMaster> getAllDepartmentService() {
        List<DepartmentMaster> allDepartments = new ArrayList<>();
        try {
            logger.info("In getAllDepartmentService");
            allDepartments = (List<DepartmentMaster>) departmentDAO.findAll();
            logger.info("Out of getAllDepartmentService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allDepartments;
    }
}
