package com.SuyogHospital.ServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Master.PunchingMaster;
import com.SuyogHospital.Master.ShiftMaster;
import com.SuyogHospital.Master.SisterHistoryMaster;
import com.SuyogHospital.Master.SisterMaster;
import com.SuyogHospital.Repository.PunchingDAO;
import com.SuyogHospital.Repository.SisterDAO;
import com.SuyogHospital.Repository.SisterHistoryDAO;
import com.SuyogHospital.ResponseDTO.SisterResponse;
import com.SuyogHospital.Service.EmployeeService;
import com.SuyogHospital.Service.ShiftService;
import com.SuyogHospital.Service.SisterService;

@Service
public class SisterServiceImpl implements SisterService {

    private static final Logger logger = LogManager.getLogger(SisterServiceImpl.class);

    @Autowired
    private SisterDAO sisterDAO;

    @Autowired
    private SisterHistoryDAO sisterHistoryDAO;

    @Autowired
    private PunchingDAO punchingDAO;
    
    @Autowired
    private ShiftService shiftService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Boolean addSister(SisterMaster sisterMaster) {
        try {
            logger.info("In addSister");

            // Validate and set shift
            if (sisterMaster.getShift() != null && sisterMaster.getShift().getShiftId() != null) {
                ShiftMaster shift = shiftService.getShiftService(sisterMaster.getShift().getShiftId());
                if (shift == null) {
                    throw new RuntimeException("Invalid Shift ID.");
                }
                sisterMaster.setShift(shift);
            } else {
                throw new RuntimeException("Shift ID is required.");
            }

            // Validate and set employee
            if (sisterMaster.getEmployee() != null && sisterMaster.getEmployee().getEmployeeId() != null) {
                EmployeeMaster employee = employeeService.getEmployeeService(sisterMaster.getEmployee().getEmployeeId());
                if (employee == null) {
                    throw new RuntimeException("Invalid Employee ID.");
                }
                sisterMaster.setEmployee(employee);
            } else {
                throw new RuntimeException("Employee ID is required.");
            }

            LocalDateTime now = LocalDateTime.now();
            sisterMaster.setDate(now.toLocalDate());
            sisterMaster.setTime(now.toLocalTime());

            sisterDAO.save(sisterMaster);
            saveSisterHistory(sisterMaster, "ADD");

            logger.info("Out of addSister");
            return true;
        } catch (Exception e) {
            logger.error("Error in addSister: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateSister(SisterMaster sisterMaster) {
        try {
            logger.info("In updateSister");

            SisterMaster existing = sisterDAO.findById(sisterMaster.getSisterId())
                    .orElseThrow(() -> new RuntimeException("Sister not found"));

            existing.setSisterFirstName(sisterMaster.getSisterFirstName());
            existing.setSisterLastName(sisterMaster.getSisterLastName());
            existing.setDutyDay(sisterMaster.getDutyDay());
            existing.setDutyDate(sisterMaster.getDutyDate());
            existing.setDutyInTime(sisterMaster.getDutyInTime());
            existing.setDutyOutTime(sisterMaster.getDutyOutTime());

            // Validate and set shift if present
            if (sisterMaster.getShift() != null && sisterMaster.getShift().getShiftId() != null) {
                ShiftMaster shift = shiftService.getShiftService(sisterMaster.getShift().getShiftId());
                if (shift == null) {
                    throw new RuntimeException("Invalid Shift ID.");
                }
                existing.setShift(shift);
            }

            // Validate and set employee if present
            if (sisterMaster.getEmployee() != null && sisterMaster.getEmployee().getEmployeeId() != null) {
                EmployeeMaster employee = employeeService.getEmployeeService(sisterMaster.getEmployee().getEmployeeId());
                if (employee == null) {
                    throw new RuntimeException("Invalid Employee ID.");
                }
                existing.setEmployee(employee);
            }

            sisterDAO.save(existing);
            saveSisterHistory(existing, "UPDATE");

            logger.info("Out of updateSister");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateSister: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteSister(SisterMaster sisterMaster) {
        try {
            logger.info("In deleteSister");

            SisterMaster existing = sisterDAO.findById(sisterMaster.getSisterId())
                    .orElseThrow(() -> new RuntimeException("Sister not found"));

            saveSisterHistory(existing, "DELETE");
            sisterDAO.delete(existing);

            logger.info("Out of deleteSister");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteSister: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public SisterMaster getSister(Integer sisterId) {
        try {
            logger.info("In getSister");
            return sisterDAO.findById(sisterId).orElse(null);
        } catch (Exception e) {
            logger.error("Error in getSister: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<SisterMaster> getAllSisters() {
        try {
            logger.info("In getAllSisters");
            return (List<SisterMaster>) sisterDAO.findAll();
        } catch (Exception e) {
            logger.error("Error in getAllSisters: " + e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private void saveSisterHistory(SisterMaster master, String action) {
        SisterHistoryMaster history = new SisterHistoryMaster();
        history.setSisterId(master.getSisterId());
        history.setSisterFirstName(master.getSisterFirstName());
        history.setSisterLastName(master.getSisterLastName());
        history.setDutyDay(master.getDutyDay());
        history.setDutyDate(master.getDutyDate());
        history.setDutyInTime(master.getDutyInTime());
        history.setDutyOutTime(master.getDutyOutTime());
        history.setAction(action);
        history.setTimestamp(LocalDateTime.now());

        sisterHistoryDAO.save(history);
    }
    
    
    //Records 
    @Override
    public List<SisterResponse> getSisterRecords(Integer employeeId, String month) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        YearMonth yearMonth = YearMonth.parse(month, formatter);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<PunchingMaster> punchingList = punchingDAO.findByEmployeeEmployeeIdAndPunchingDateBetween(
                employeeId, startDate, endDate
        );

        List<SisterResponse> responseList = new ArrayList<>();

        for (PunchingMaster punching : punchingList) {

            Optional<SisterMaster> sisterOpt = sisterDAO.findByEmployeeEmployeeIdAndDutyDate(
                    employeeId, punching.getPunchingDate()
            );

            SisterResponse response = new SisterResponse();
            response.setEmployeeName(
                punching.getEmployee().getEmployeeFirstName() + " " + punching.getEmployee().getEmployeeLastName()
            );
            response.setShift(punching.getShift().getShiftName());
            response.setDutyDay(punching.getPunchingDay());
            response.setPunchingInTime(punching.getInTime() != null ? punching.getInTime().toString() : "-");
            response.setPunchingOutTime(punching.getOutTime() != null ? punching.getOutTime().toString() : "-");

            if (sisterOpt.isPresent()) {
                SisterMaster sister = sisterOpt.get();
                response.setDutyInTime(sister.getDutyInTime() != null ? sister.getDutyInTime().toString() : "-");
                response.setDutyOutTime(sister.getDutyOutTime() != null ? sister.getDutyOutTime().toString() : "-");
            } else {
                response.setDutyInTime("-");
                response.setDutyOutTime("-");
            }

            responseList.add(response);
        }

        return responseList;
    }
    
}
