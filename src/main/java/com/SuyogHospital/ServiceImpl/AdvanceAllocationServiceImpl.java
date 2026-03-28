package com.SuyogHospital.ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.AdvanceAllocationHistoryMaster;
import com.SuyogHospital.Master.AdvanceAllocationMaster;
import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Repository.AdvanceAllocationDAO;
import com.SuyogHospital.Repository.AdvanceAllocationHistoryDAO;
import com.SuyogHospital.Service.AdvanceAllocationService;
import com.SuyogHospital.Service.EmployeeService;

@Service
public class AdvanceAllocationServiceImpl implements AdvanceAllocationService {

    private static final Logger logger = LogManager.getLogger(AdvanceAllocationServiceImpl.class);

    @Autowired
    private AdvanceAllocationDAO advanceDAO;

    @Autowired
    private AdvanceAllocationHistoryDAO historyDAO;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Boolean addAdvanceAllocationService(AdvanceAllocationMaster allocation) {
        try {
            logger.info("In addAdvanceAllocationService");
            LocalDateTime now = LocalDateTime.now();
            allocation.setDate(now.toLocalDate());
            allocation.setTime(now.toLocalTime());

            EmployeeMaster employee = employeeService.getEmployeeService(allocation.getEmployee().getEmployeeId());
            if (employee != null) {
                allocation.setEmployee(employee);
            } else {
                logger.error("Invalid Employee ID");
                return false;
            }

            advanceDAO.save(allocation);

            // ✅ Save History
            AdvanceAllocationHistoryMaster history = new AdvanceAllocationHistoryMaster();
            copyToHistory(allocation, history, "Create", now);
            historyDAO.save(history);

            logger.info("Out of addAdvanceAllocationService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateAdvanceAllocationService(AdvanceAllocationMaster allocation) {
        try {
            logger.info("In updateAdvanceAllocationService");

            AdvanceAllocationMaster existing = advanceDAO.findById(allocation.getAllocationId())
                    .orElseThrow(() -> new RuntimeException("Allocation not found"));

            LocalDateTime now = LocalDateTime.now();

            // ✅ Save old version in history
            AdvanceAllocationHistoryMaster history = new AdvanceAllocationHistoryMaster();
            copyToHistory(existing, history, "Update", now);

            // ✅ Update fields
            existing.setAdvanceAmountDate(allocation.getAdvanceAmountDate());
            existing.setAmount(allocation.getAmount());
            existing.setIpd(allocation.getIpd());
            existing.setOpd(allocation.getOpd());
            existing.setSalaryAdvanced(allocation.getSalaryAdvanced());
            existing.setOthers(allocation.getOthers());
            existing.setRecoveryFromSalary(allocation.getRecoveryFromSalary());
            existing.setBalance(allocation.getBalance());
            existing.setApprovedBy(allocation.getApprovedBy());
            existing.setCreatedBy(allocation.getCreatedBy());
            existing.setRemarks(allocation.getRemarks());

            advanceDAO.save(existing);
            historyDAO.save(history);

            logger.info("Out of updateAdvanceAllocationService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteAdvanceAllocationService(AdvanceAllocationMaster allocation) {
        try {
            logger.info("In deleteAdvanceAllocationService");

            AdvanceAllocationMaster existing = advanceDAO.findById(allocation.getAllocationId())
                    .orElseThrow(() -> new RuntimeException("Allocation not found"));

            AdvanceAllocationHistoryMaster history = new AdvanceAllocationHistoryMaster();
            copyToHistory(existing, history, "Delete", LocalDateTime.now());

            advanceDAO.delete(existing);
            historyDAO.save(history);

            logger.info("Out of deleteAdvanceAllocationService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public AdvanceAllocationMaster getAdvanceAllocationService(Integer allocationId) {
        try {
            logger.info("In getAdvanceAllocationService");
            return advanceDAO.findById(allocationId).orElse(null);
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<AdvanceAllocationMaster> getAllAdvanceAllocationService() {
        try {
            logger.info("In getAllAdvanceAllocationService");
            return (List<AdvanceAllocationMaster>) advanceDAO.findAll();
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private void copyToHistory(AdvanceAllocationMaster src, AdvanceAllocationHistoryMaster dest,
                               String action, LocalDateTime timestamp) {
        dest.setAllocationId(src.getAllocationId());
        dest.setAdvanceAmountDate(src.getAdvanceAmountDate());
        dest.setAmount(src.getAmount());
        dest.setIpd(src.getIpd());
        dest.setOpd(src.getOpd());
        dest.setSalaryAdvanced(src.getSalaryAdvanced());
        dest.setOthers(src.getOthers());
        dest.setRecoveryFromSalary(src.getRecoveryFromSalary());
        dest.setBalance(src.getBalance());
        dest.setApprovedBy(src.getApprovedBy());
        dest.setCreatedBy(src.getCreatedBy());
        dest.setRemarks(src.getRemarks());
        dest.setEmployeeId(src.getEmployee().getEmployeeId());
        dest.setAction(action);
        dest.setTimestamp(timestamp);
    }

    @Override
    public BigDecimal calculatePreviousBalance(Integer employeeId, Integer currentAllocationId) {
        try {
            // ✅ Fetch all allocations for the employee
            List<AdvanceAllocationMaster> allocations = advanceDAO.findByEmployeeEmployeeIdOrderByAllocationIdAsc(employeeId);

            BigDecimal previousBalance = BigDecimal.ZERO;

            for (AdvanceAllocationMaster allocation : allocations) {
                if (allocation.getAllocationId() < currentAllocationId) {
                    BigDecimal total = allocation.calculateTotalAdvance()
                            .subtract(allocation.getRecoveryFromSalary() != null ? allocation.getRecoveryFromSalary() : BigDecimal.ZERO);
                    previousBalance = previousBalance.add(total);
                }
            }
            return previousBalance;
        } catch (Exception e) {
            logger.error("Error calculating previous balance", e);
            return BigDecimal.ZERO;
        }
    }
    
    @Override
    public double getPreviousBalanceByEmployee(Long employeeId) {
        List<Double> balances = advanceDAO.findLatestBalanceByEmployee(employeeId.intValue());
        return balances.isEmpty() ? 0.0 : balances.get(0); // get the first (latest) balance
    }

}
