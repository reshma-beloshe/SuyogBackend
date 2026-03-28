package com.SuyogHospital.ServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Master.ReceptionHistoryMaster;
import com.SuyogHospital.Master.ReceptionMaster;
import com.SuyogHospital.Master.ShiftMaster;
import com.SuyogHospital.Repository.ReceptionDAO;
import com.SuyogHospital.Repository.ReceptionHistoryDAO;
import com.SuyogHospital.ResponseDTO.ReceptionDTO;
import com.SuyogHospital.Service.EmployeeService;
import com.SuyogHospital.Service.ReceptionService;
import com.SuyogHospital.Service.ShiftService;

@Service
public class ReceptionServiceImpl implements ReceptionService {

    private static final Logger logger = LogManager.getLogger(ReceptionServiceImpl.class);

    @Autowired
    private ReceptionDAO receptionDAO;

    @Autowired
    private ReceptionHistoryDAO receptionHistoryDAO;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ShiftService shiftService;

    @Override
    public Boolean addReceptionService(ReceptionDTO dto) {
        try {
            // Input validation
            if (dto.getEmployeeId() == null || dto.getShiftId() == null || dto.getReceptionDate() == null) {
                System.out.println("❌ Validation failed: Missing required fields");
                return false;
            }

            // Fetch employee and shift
            EmployeeMaster employee = employeeService.getEmployeeService(dto.getEmployeeId());
            if (employee == null) {
                System.out.println("❌ Employee not found");
                return false;
            }

            ShiftMaster shift = shiftService.getShiftService(dto.getShiftId());
            if (shift == null) {
                System.out.println("❌ Shift not found");
                return false;
            }

            // Handle existing entries
            List<ReceptionMaster> existingReceptions = receptionDAO
                .findAllByEmployeeEmployeeIdAndReceptionDate(dto.getEmployeeId(), dto.getReceptionDate());

            ReceptionMaster reception;
            String action;

            if (!existingReceptions.isEmpty()) {
                // Take the first record and delete any duplicates
                reception = existingReceptions.get(0);
                action = "UPDATE";
                
                if (existingReceptions.size() > 1) {
                    for (int i = 1; i < existingReceptions.size(); i++) {
                        receptionDAO.delete(existingReceptions.get(i));
                    }
                    System.out.println("⚠️ Deleted " + (existingReceptions.size()-1) + " duplicates");
                }
            } else {
                reception = new ReceptionMaster();
                reception.setDate(LocalDate.now());
                reception.setEmployee(employee);
                reception.setCreatedBy(true);
                action = "ADD";
            }

            // Update reception data
            reception.setReceptionDate(dto.getReceptionDate());
            reception.setTime(dto.getTime());
            reception.setMonth(dto.getMonth());
            reception.setYear(dto.getYear());
            reception.setRemarks(dto.getRemarks());
            reception.setShift(shift);
            reception.setApprovedBy(false);

            // Save and log
            receptionDAO.save(reception);
            saveHistory(reception, action);
            System.out.println("✅ Reception " + action + " successful");
            return true;

        } catch (Exception e) {
            System.out.println("❌ Exception: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public Boolean updateReceptionService(ReceptionDTO dto) {
        try {
            logger.info("In updateReceptionService");

            ReceptionMaster existing = receptionDAO.findById(dto.getReceptionId())
                    .orElseThrow(() -> new RuntimeException("Reception not found"));

            if (existing.getReceptionDate() != null && existing.getReceptionDate().isBefore(LocalDate.now())) {
                logger.warn("Cannot update past-date reception: " + existing.getReceptionDate());
                throw new IllegalArgumentException("Failed to update reception. Past-date entries cannot be modified.");
            }

            EmployeeMaster employee = employeeService.getEmployeeService(dto.getEmployeeId());
            ShiftMaster shift = shiftService.getShiftService(dto.getShiftId());

            if (employee == null || shift == null) {
                logger.error("Invalid employee or shift ID");
                return false;
            }

            existing.setEmployee(employee);
            existing.setShift(shift);
            existing.setMonth(dto.getMonth());
            existing.setYear(dto.getYear());
            existing.setReceptionDate(dto.getReceptionDate());
            existing.setRemarks(dto.getRemarks());
            existing.setApprovedBy(false);
            existing.setCreatedBy(true);

            receptionDAO.save(existing);
            saveHistory(existing, "UPDATE");

            logger.info("Out of updateReceptionService");
            return true;

        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());
            return false;
        } catch (Exception e) {
            logger.error("ReceptionService Error: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteReceptionService(ReceptionMaster reception) {
        try {
            logger.info("In deleteReceptionService");

            ReceptionMaster existing = receptionDAO.findById(reception.getReceptionId())
                    .orElseThrow(() -> new RuntimeException("Reception not found"));

            if (existing.getReceptionDate().isBefore(LocalDate.now())) {
                logger.warn("Cannot delete past-date reception: " + existing.getReceptionDate());
                return false;
            }

            saveHistory(existing, "DELETE");
            receptionDAO.delete(existing);

            logger.info("Out of deleteReceptionService");
            return true;
        } catch (Exception e) {
            logger.error("ReceptionService Error: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public ReceptionMaster getReceptionService(Integer receptionId) {
        try {
            logger.info("In getReceptionService");
            return receptionDAO.findById(receptionId).orElse(null);
        } catch (Exception e) {
            logger.error("ReceptionService Error: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<ReceptionMaster> getAllReceptionService() {
        List<ReceptionMaster> list = new ArrayList<>();
        try {
            logger.info("In getAllReceptionService");
            list = (List<ReceptionMaster>) receptionDAO.findAll();
        } catch (Exception e) {
            logger.error("ReceptionService Error: " + e.getMessage(), e);
        }
        return list;
    }

    private void saveHistory(ReceptionMaster reception, String action) {
        ReceptionHistoryMaster history = new ReceptionHistoryMaster();

        history.setReceptionId(reception.getReceptionId());
        history.setMonth(reception.getMonth());
        history.setYear(reception.getYear());
        history.setApprovedBy(reception.getApprovedBy());
        history.setCreatedBy(reception.getCreatedBy());
        history.setDate(reception.getDate());
        history.setTime(reception.getTime());
        history.setRemarks(reception.getRemarks());
        history.setReceptionDate(reception.getReceptionDate());

        if (reception.getEmployee() != null) {
            history.setEmployeeId(reception.getEmployee().getEmployeeId());
        }
        if (reception.getShift() != null) {
            history.setShiftId(reception.getShift().getShiftId());
        }

        history.setAction(action);
        history.setTimestamp(LocalDateTime.now());

        receptionHistoryDAO.save(history);
    }
    
    @Override
    public List<ReceptionDTO> getAllReceptionsByMonthAndYear(int month, int year) {
        List<ReceptionMaster> receptions = receptionDAO.findByMonthAndYear(month, year);
        return receptions.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    private ReceptionDTO convertToDto(ReceptionMaster reception) {
        ReceptionDTO dto = new ReceptionDTO();
        dto.setReceptionId(reception.getReceptionId());
        dto.setEmployeeId(reception.getEmployee().getEmployeeId());
        dto.setShiftId(reception.getShift().getShiftId());
        dto.setMonth(reception.getMonth());
        dto.setYear(reception.getYear());
        dto.setReceptionDate(reception.getReceptionDate());
        dto.setRemarks(reception.getRemarks());
        return dto;
    }


}
