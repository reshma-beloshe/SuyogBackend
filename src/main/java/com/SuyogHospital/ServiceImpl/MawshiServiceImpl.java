package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Master.MawshiHistoryMaster;
import com.SuyogHospital.Master.MawshiMaster;
import com.SuyogHospital.Master.PunchingMaster;
import com.SuyogHospital.Repository.EmployeeDAO;
import com.SuyogHospital.Repository.MawshiDAO;
import com.SuyogHospital.Repository.MawshiHistoryDAO;
import com.SuyogHospital.Repository.PunchingDAO;
import com.SuyogHospital.ResponseDTO.MawshiResponse;
import com.SuyogHospital.Service.MawshiService;

@Service
public class MawshiServiceImpl implements MawshiService {

    private static final Logger logger = LogManager.getLogger(MawshiServiceImpl.class);

    @Autowired
    private MawshiDAO mawshiDAO;

    @Autowired
    private MawshiHistoryDAO mawshiHistoryDAO;
    
    @Autowired
    private PunchingDAO punchingDAO;
    
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public Boolean addMawshi(MawshiMaster mawshiMaster) {
        try {
            logger.info("In addMawshi");
            LocalDateTime now = LocalDateTime.now();
            mawshiMaster.setDate(now.toLocalDate());
            mawshiMaster.setTime(now.toLocalTime());

            mawshiDAO.save(mawshiMaster);
            saveMawshiHistory(mawshiMaster, "ADD");

            logger.info("Out of addMawshi");
            return true;
        } catch (Exception e) {
            logger.error("Error in addMawshi: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateMawshi(MawshiMaster mawshiMaster) {
        try {
            logger.info("In updateMawshi");

            MawshiMaster existing = mawshiDAO.findById(mawshiMaster.getMawshiId())
                    .orElseThrow(() -> new RuntimeException("Mawshi not found"));

            existing.setMawshiFirstName(mawshiMaster.getMawshiFirstName());
            existing.setMawshiLastName(mawshiMaster.getMawshiLastName());
            existing.setDutyDay(mawshiMaster.getDutyDay());
            existing.setDutyDate(mawshiMaster.getDutyDate());
            existing.setDutyInTime(mawshiMaster.getDutyInTime());
            existing.setDutyOutTime(mawshiMaster.getDutyOutTime());

            if (mawshiMaster.getShift() != null) {
                existing.setShift(mawshiMaster.getShift());
            }
            
            if (mawshiMaster.getEmployee() != null) {
                existing.setEmployee(mawshiMaster.getEmployee());
            }
            
            mawshiDAO.save(existing);
            saveMawshiHistory(existing, "UPDATE");

            logger.info("Out of updateMawshi");
            return true;
        } catch (Exception e) {
            logger.error("Error in updateMawshi: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteMawshi(MawshiMaster mawshiMaster) {
        try {
            logger.info("In deleteMawshi");

            MawshiMaster existing = mawshiDAO.findById(mawshiMaster.getMawshiId())
                    .orElseThrow(() -> new RuntimeException("Mawshi not found"));

            saveMawshiHistory(existing, "DELETE");
            mawshiDAO.delete(existing);

            logger.info("Out of deleteMawshi");
            return true;
        } catch (Exception e) {
            logger.error("Error in deleteMawshi: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public MawshiMaster getMawshi(Integer mawshiId) {
        try {
            logger.info("In getMawshi");
            return mawshiDAO.findById(mawshiId).orElse(null);
        } catch (Exception e) {
            logger.error("Error in getMawshi: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<MawshiMaster> getAllMawshis() {
        try {
            logger.info("In getAllMawshis");
            return (List<MawshiMaster>) mawshiDAO.findAll();
        } catch (Exception e) {
            logger.error("Error in getAllMawshis: " + e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private void saveMawshiHistory(MawshiMaster master, String action) {
        MawshiHistoryMaster history = new MawshiHistoryMaster();
        history.setMawshiId(master.getMawshiId());
        history.setMawshiFirstName(master.getMawshiFirstName());
        history.setMawshiLastName(master.getMawshiLastName());
        history.setDutyDay(master.getDutyDay());
        history.setDutyDate(master.getDutyDate());
        history.setDutyInTime(master.getDutyInTime());
        history.setDutyOutTime(master.getDutyOutTime());
        history.setAction(action);
        history.setTimestamp(LocalDateTime.now());

        // Add employeeId to history (assuming you have this column in MawshiHistoryMaster)
        if (master.getEmployee() != null) {
            history.setEmployeeId(master.getEmployee().getEmployeeId());
        }

        mawshiHistoryDAO.save(history);
    }
    
    
    
    //Records 
    @Override
    public List<MawshiResponse> getMawshiRecords(Integer employeeId, String monthYear) {

        String[] parts = monthYear.split("-");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);

        // Fetch Punching Records
        List<PunchingMaster> punchingList = punchingDAO.findByEmployeeAndMonthAndYear(employeeId, month, year);
        // Fetch Mawshi Duty Records
        List<MawshiMaster> mawshiList = mawshiDAO.findByEmployeeAndMonthAndYear(employeeId, month, year);
        // Fetch Employee Data
        EmployeeMaster employee = employeeDAO.findById(employeeId).orElse(null);

        List<MawshiResponse> responseList = new ArrayList<>();

        for (PunchingMaster p : punchingList) {
            MawshiResponse response = new MawshiResponse();
            response.setEmployeeName(employee.getEmployeeFirstName() + " " + employee.getEmployeeLastName());
            response.setPunchingInTime(p.getInTime() != null ? p.getInTime().toString() : "-");
            response.setPunchingOutTime(p.getOutTime() != null ? p.getOutTime().toString() : "-");
            response.setDutyDay(p.getPunchingDay());
            response.setDutyDate(p.getPunchingDate().toString());
            response.setShift(p.getShift().getShiftName());

            // Find matching duty date
            MawshiMaster matchingDuty = mawshiList.stream()
                    .filter(m -> m.getDutyDate().equals(p.getPunchingDate()))
                    .findFirst()
                    .orElse(null);

            if (matchingDuty != null) {
                response.setDutyInTime(matchingDuty.getDutyInTime() != null ? matchingDuty.getDutyInTime().toString() : "-");
                response.setDutyOutTime(matchingDuty.getDutyOutTime() != null ? matchingDuty.getDutyOutTime().toString() : "-");
            } else {
                response.setDutyInTime("-");
                response.setDutyOutTime("-");
            }

            responseList.add(response);
        }

        return responseList;
    }
}
