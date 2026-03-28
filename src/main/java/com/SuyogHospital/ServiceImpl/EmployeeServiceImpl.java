package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.EmployeeHistoryMaster;
import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Repository.EmployeeDAO;
import com.SuyogHospital.Repository.EmployeeHistoryDAO;
import com.SuyogHospital.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private EmployeeHistoryDAO employeeHistoryDAO;

    @Override
    public Boolean addEmployeeService(EmployeeMaster employee) {
        try {
            logger.info("In addEmployeeService");
            LocalDateTime now = LocalDateTime.now();
            employee.setDate(now.toLocalDate());
            employee.setTime(now.toLocalTime());

            employeeDAO.save(employee);

            EmployeeHistoryMaster history = prepareHistory(employee, "Create", now);
            employeeHistoryDAO.save(history);

            logger.info("Out of addEmployeeService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateEmployeeService(EmployeeMaster employee) {
        try {
            logger.info("In updateEmployeeService");

            EmployeeMaster existing = employeeDAO.findById(employee.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            LocalDateTime now = LocalDateTime.now();

            EmployeeHistoryMaster history = prepareHistory(existing, "Update", now);

            existing.setEmployeeFirstName(employee.getEmployeeFirstName());
            existing.setEmployeeLastName(employee.getEmployeeLastName());
            existing.setGender(employee.getGender());
            existing.setBirthDate(employee.getBirthDate());
            existing.setMobileNo(employee.getMobileNo());
            existing.setEmailId(employee.getEmailId());
            existing.setDateOfJoining(employee.getDateOfJoining());
            existing.setStatus(employee.getStatus());
            existing.setApproved(employee.isApproved());
            existing.setApprovedBy(employee.getApprovedBy());
            existing.setBankAcNo(employee.getBankAcNo());
            existing.setAcType(employee.getAcType());
            existing.setSalaryMode(employee.getSalaryMode());
            existing.setPanNo(employee.getPanNo());
            existing.setlAddress(employee.getlAddress());
            existing.setlPin(employee.getlPin());
            existing.setpAddress(employee.getpAddress());
            existing.setpPin(employee.getpPin());

            // Set relationships safely
            if (employee.getDepartment() != null)
                existing.setDepartment(employee.getDepartment());

            if (employee.getDesignation() != null)
                existing.setDesignation(employee.getDesignation());

            if (employee.getEmpCategory() != null)
                existing.setEmpCategory(employee.getEmpCategory());

            if (employee.getArea() != null)
                existing.setArea(employee.getArea());

            if (employee.getBank() != null)
                existing.setBank(employee.getBank());

            employeeDAO.save(existing);
            employeeHistoryDAO.save(history);

            logger.info("Out of updateEmployeeService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteEmployeeService(EmployeeMaster employee) {
        try {
            logger.info("In deleteEmployeeService");

            EmployeeMaster existing = employeeDAO.findById(employee.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            EmployeeHistoryMaster history = prepareHistory(existing, "Delete", LocalDateTime.now());

            employeeDAO.delete(existing);
            employeeHistoryDAO.save(history);

            logger.info("Out of deleteEmployeeService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public EmployeeMaster getEmployeeService(Integer employeeId) {
        try {
            logger.info("In getEmployeeService");
            return employeeDAO.findById(employeeId).orElse(null);
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<EmployeeMaster> getAllEmployeeService() {
        try {
            logger.info("In getAllEmployeeService");
            return (List<EmployeeMaster>) employeeDAO.findAll();
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private EmployeeHistoryMaster prepareHistory(EmployeeMaster employee, String action, LocalDateTime timestamp) {
        EmployeeHistoryMaster history = new EmployeeHistoryMaster();

        history.setEmployeeId(employee.getEmployeeId());
        history.setEmployeeFirstName(employee.getEmployeeFirstName());
        history.setEmployeeLastName(employee.getEmployeeLastName());
        history.setGender(employee.getGender());
        history.setBirthDate(employee.getBirthDate());
        history.setMobileNo(employee.getMobileNo());
        history.setEmailId(employee.getEmailId());
        history.setDateOfJoining(employee.getDateOfJoining());
        history.setStatus(employee.getStatus());
        history.setApproved(employee.isApproved());
        history.setApprovedBy(employee.getApprovedBy());
        history.setBankAcNo(employee.getBankAcNo());
        history.setAcType(employee.getAcType());
        history.setSalaryMode(employee.getSalaryMode());
        history.setPanNo(employee.getPanNo());
        history.setlAddress(employee.getlAddress());
        history.setlPin(employee.getlPin());
        history.setpAddress(employee.getpAddress());
        history.setpPin(employee.getpPin());

        // Null-safe relationship mapping
        history.setDepartmentId(
            employee.getDepartment() != null && employee.getDepartment().getDepartmentId() != null
                ? employee.getDepartment().getDepartmentId()
                : 0
        );

        history.setDesignationId(
            employee.getDesignation() != null && employee.getDesignation().getId() != null
                ? employee.getDesignation().getId()
                : 0
        );

        history.setEmpCategoryId(
            employee.getEmpCategory() != null && employee.getEmpCategory().getEmpCategoryId() != null
                ? employee.getEmpCategory().getEmpCategoryId()
                : 0
        );

        history.setAreaId(
            employee.getArea() != null && employee.getArea().getAreaId() != null
                ? employee.getArea().getAreaId()
                : 0
        );

        history.setBankId(
            employee.getBank() != null && employee.getBank().getBankId() != null
                ? employee.getBank().getBankId()
                : 0
        );

        history.setAction(action);
        history.setTimestamp(timestamp);
        history.setDate(timestamp.toLocalDate());
        history.setTime(timestamp.toLocalTime());

        return history;
    }
}
