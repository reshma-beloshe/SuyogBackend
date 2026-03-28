package com.SuyogHospital.ServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.EmployeeFinancialHistoryMaster;
import com.SuyogHospital.Master.EmployeeFinancialMaster;
import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Master.PunchingMaster;
import com.SuyogHospital.Repository.EmployeeDAO;
import com.SuyogHospital.Repository.EmployeeFinancialDAO;
import com.SuyogHospital.Repository.EmployeeFinancialHistoryDAO;
import com.SuyogHospital.Repository.PunchingDAO;
import com.SuyogHospital.ResponseDTO.EmployeeFinancialDTO;
import com.SuyogHospital.ResponseDTO.EmployeeFinancialWithPunchingResponse;
import com.SuyogHospital.Service.EmployeeFinancialService;

@Service
public class EmployeeFinancialServiceImpl implements EmployeeFinancialService {

 private static final Logger logger = LogManager.getLogger(EmployeeFinancialServiceImpl.class);

 @Autowired
 private EmployeeFinancialDAO employeeFinancialDAO;

 @Autowired
 private EmployeeFinancialHistoryDAO employeeFinancialHistoryDAO;
 
 @Autowired
 private PunchingDAO punchingDAO;
 
 @Autowired
 private EmployeeDAO employeeDAO;

 @Override
 public Boolean addEmployeeFinancialService(EmployeeFinancialMaster employeeFinancialMaster) {
     try {
         logger.info("In addEmployeeFinancialService");

         LocalDateTime now = LocalDateTime.now();
         employeeFinancialMaster.setDate(now.toLocalDate());
         employeeFinancialMaster.setTime(now.toLocalTime());
         employeeFinancialDAO.save(employeeFinancialMaster);

         // Create and save history record
         EmployeeFinancialHistoryMaster history = new EmployeeFinancialHistoryMaster();
         history.setEmpFinancialId(employeeFinancialMaster.getEmpFinancialId()); // Link to the new record
         history.setEmpOTGross(employeeFinancialMaster.getEmpOTGross());
         history.setEmpBasic(employeeFinancialMaster.getEmpBasic());
         history.setEmpDA(employeeFinancialMaster.getEmpDA());
         history.setEmpDiffDAPreviousMonth(employeeFinancialMaster.getEmpDiffDAPreviousMonth());
         history.setEmpHRAAllowance(employeeFinancialMaster.getEmpHRAAllowance());
         history.setEmpOtherAllowance(employeeFinancialMaster.getEmpOtherAllowance());
         history.setEmpProfessionalTax(employeeFinancialMaster.getEmpProfessionalTax());
         history.setLabour(employeeFinancialMaster.getLabour());
         history.setAdvanceAmount(employeeFinancialMaster.getAdvanceAmount());
         // Assuming employeeId can be extracted from EmployeeMaster or passed separately
         history.setEmployeeId(employeeFinancialMaster.getEmployee().getEmployeeId()); // Assuming getEmployeeId() exists in EmployeeMaster
         history.setDate(now.toLocalDate());
         history.setTime(now.toLocalTime());
         history.setAction("Create");
         history.setTimestamp(now);
         // Assuming addedBy, updatedBy, approvedBy, isApproved are handled in the entity or passed
         // history.setAddedBy(employeeFinancialMaster.getAddedBy()); // Uncomment if these fields are in EmployeeFinancialMaster
         // history.setIsApproved(employeeFinancialMaster.getIsApproved());

         employeeFinancialHistoryDAO.save(history);
         logger.info("Out of addEmployeeFinancialService");
         return true;
     } catch (Exception e) {
         logger.error("IMS: Error in addEmployeeFinancialService: " + e.getMessage(), e);
         return false;
     }
 }

 @Override
 public Boolean updateEmployeeFinancialService(EmployeeFinancialMaster employeeFinancialMaster) {
     try {
         logger.info("In updateEmployeeFinancialService");

         Optional<EmployeeFinancialMaster> existingOptional = employeeFinancialDAO.findById(employeeFinancialMaster.getEmpFinancialId());
         if (existingOptional.isEmpty()) {
             logger.warn("Employee Financial record with ID {} not found for update.", employeeFinancialMaster.getEmpFinancialId());
             return false; // Or throw a specific exception
         }
         EmployeeFinancialMaster existing = existingOptional.get();

         LocalDateTime now = LocalDateTime.now();

         // Create and save history record before updating the main record
         EmployeeFinancialHistoryMaster history = new EmployeeFinancialHistoryMaster();
         history.setEmpFinancialId(existing.getEmpFinancialId());
         history.setEmpOTGross(existing.getEmpOTGross());
         history.setEmpBasic(existing.getEmpBasic());
         history.setEmpDA(existing.getEmpDA());
         history.setEmpDiffDAPreviousMonth(existing.getEmpDiffDAPreviousMonth());
         history.setEmpHRAAllowance(existing.getEmpHRAAllowance());
         history.setEmpOtherAllowance(existing.getEmpOtherAllowance());
         history.setEmpProfessionalTax(existing.getEmpProfessionalTax());
         history.setLabour(existing.getLabour());
         history.setAdvanceAmount(existing.getAdvanceAmount());
         history.setEmployeeId(existing.getEmployee().getEmployeeId()); // Assuming getEmployeeId() exists
         history.setDate(now.toLocalDate());
         history.setTime(now.toLocalTime());
         history.setAction("Update");
         history.setTimestamp(now);
         // history.setUpdatedBy(employeeFinancialMaster.getUpdatedBy()); // Uncomment if these fields are in EmployeeFinancialMaster
         // history.setIsApproved(employeeFinancialMaster.getIsApproved());

         employeeFinancialHistoryDAO.save(history);

         // Update the existing record with new values
         existing.setEmpOTGross(employeeFinancialMaster.getEmpOTGross());
         existing.setEmpBasic(employeeFinancialMaster.getEmpBasic());
         existing.setEmpDA(employeeFinancialMaster.getEmpDA());
         existing.setEmpDiffDAPreviousMonth(employeeFinancialMaster.getEmpDiffDAPreviousMonth());
         existing.setEmpHRAAllowance(employeeFinancialMaster.getEmpHRAAllowance());
         existing.setEmpOtherAllowance(employeeFinancialMaster.getEmpOtherAllowance());
         existing.setEmpProfessionalTax(employeeFinancialMaster.getEmpProfessionalTax());
         existing.setLabour(employeeFinancialMaster.getLabour());
         existing.setAdvanceAmount(employeeFinancialMaster.getAdvanceAmount());
         existing.setEmployee(employeeFinancialMaster.getEmployee()); // Update the employee reference
         existing.setDate(now.toLocalDate());
         existing.setTime(now.toLocalTime());
         // existing.setUpdatedBy(employeeFinancialMaster.getUpdatedBy()); // Uncomment if these fields are in EmployeeFinancialMaster
         // existing.setIsApproved(employeeFinancialMaster.getIsApproved());

         employeeFinancialDAO.save(existing);
         logger.info("Out of updateEmployeeFinancialService");
         return true;
     } catch (Exception e) {
         logger.error("IMS: Error in updateEmployeeFinancialService: " + e.getMessage(), e);
         return false;
     }
 }

 @Override
 public Boolean deleteEmployeeFinancialService(Integer employeeFinancialId) { // Changed parameter to ID
     try {
         logger.info("In deleteEmployeeFinancialService");

         Optional<EmployeeFinancialMaster> existingOptional = employeeFinancialDAO.findById(employeeFinancialId);
         if (existingOptional.isEmpty()) {
             logger.warn("Employee Financial record with ID {} not found for deletion.", employeeFinancialId);
             return false;
         }
         EmployeeFinancialMaster existing = existingOptional.get();

         LocalDateTime now = LocalDateTime.now();

         // Create and save history record before deleting the main record
         EmployeeFinancialHistoryMaster history = new EmployeeFinancialHistoryMaster();
         history.setEmpFinancialId(existing.getEmpFinancialId());
         history.setEmpOTGross(existing.getEmpOTGross());
         history.setEmpBasic(existing.getEmpBasic());
         history.setEmpDA(existing.getEmpDA());
         history.setEmpDiffDAPreviousMonth(existing.getEmpDiffDAPreviousMonth());
         history.setEmpHRAAllowance(existing.getEmpHRAAllowance());
         history.setEmpOtherAllowance(existing.getEmpOtherAllowance());
         history.setEmpProfessionalTax(existing.getEmpProfessionalTax());
         history.setLabour(existing.getLabour());
         history.setAdvanceAmount(existing.getAdvanceAmount());
         history.setEmployeeId(existing.getEmployee().getEmployeeId()); // Assuming getEmployeeId() exists
         history.setDate(now.toLocalDate());
         history.setTime(now.toLocalTime());
         history.setAction("Delete");
         history.setTimestamp(now);
         // history.setUpdatedBy(existing.getUpdatedBy()); // Uncomment if these fields are in EmployeeFinancialMaster
         // history.setIsApproved(existing.getIsApproved());

         employeeFinancialHistoryDAO.save(history);

         employeeFinancialDAO.delete(existing); // Delete the main record
         logger.info("Out of deleteEmployeeFinancialService");
         return true;
     } catch (Exception e) {
         logger.error("IMS: Error in deleteEmployeeFinancialService: " + e.getMessage(), e);
         return false;
     }
 }

 @Override
 public EmployeeFinancialMaster getEmployeeFinancialService(Integer empFinancialId) {
     try {
         logger.info("In getEmployeeFinancialService");
         EmployeeFinancialMaster employeeFinancialMaster = employeeFinancialDAO.findById(empFinancialId).orElse(null);
         logger.info("Out of getEmployeeFinancialService");
         return employeeFinancialMaster;
     } catch (Exception e) {
         logger.error("IMS: Error in getEmployeeFinancialService: " + e.getMessage(), e);
         return null;
     }
 }

 @Override
 public List<EmployeeFinancialMaster> getAllEmployeeFinancialServices() {
     Iterable<EmployeeFinancialMaster> allEmployeeFinancialRecords = new ArrayList<>();
     try {
         logger.info("In getAllEmployeeFinancialService");
         allEmployeeFinancialRecords = employeeFinancialDAO.findAll();
         logger.info("Out of getAllEmployeeFinancialService");
     } catch (Exception e) {
         logger.error("IMS: Error in getAllEmployeeFinancialService: " + e.getMessage(), e);
     }
     return (List<EmployeeFinancialMaster>) allEmployeeFinancialRecords;
 }
 
 @Override
 public List<EmployeeFinancialWithPunchingResponse> getAllFinancialDetailsWithPunchingByMonthAndYear(int month, int year) {
     if (month < 1 || month > 12 || year < 1900) {
         throw new IllegalArgumentException("Invalid month or year");
     }

     List<EmployeeMaster> allEmployees = (List<EmployeeMaster>) employeeDAO.findAll();

     return allEmployees.stream()
         .map(emp -> {
             EmployeeFinancialWithPunchingResponse response =
                 getAllFinancialDetailsWithPunchingByEmployeeId(emp.getEmployeeId(), month, year);

             if (response != null) {
                 response.setEmployeeFirstName(emp.getEmployeeFirstName());
                 response.setEmployeeLastName(emp.getEmployeeLastName());// ✅ Set employee name here
             }

             return response;
         })
         .filter(data ->
             data != null && (
                 !data.getFinancialRecords().isEmpty() ||
                 data.getWorkingDays() > 0 ||
                 data.getPresentDays() > 0 ||
                 data.getOTDays() > 0
             )
         )
         .collect(Collectors.toList());
 }
 
 
 
 @Override
 public EmployeeFinancialWithPunchingResponse getAllFinancialDetailsWithPunchingByEmployeeId(Integer employeeId, int month, int year) {
     if (employeeId == null || month < 1 || month > 12 || year < 1900) {
         throw new IllegalArgumentException("Invalid input parameters");
     }

     LocalDate startDate = LocalDate.of(year, month, 1);
     LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
     int totalDaysInMonth = startDate.lengthOfMonth();

     List<PunchingMaster> punchingRecords = punchingDAO
         .findByEmployeeEmployeeIdAndPunchingDateBetween(employeeId, startDate, endDate);

     Duration totalDuration = Duration.ZERO;
     for (PunchingMaster record : punchingRecords) {
         if (record.getInTime() != null && record.getOutTime() != null) {
             LocalTime in = record.getInTime();
             LocalTime out = record.getOutTime();

             Duration dailyDuration = out.isAfter(in)
                 ? Duration.between(in, out)
                 : Duration.between(out, in);

             totalDuration = totalDuration.plus(dailyDuration);
             System.out.println("\n >>>>>>>> in : " + in + "\t out : " + out + "\t totalDuration : " + ((double) dailyDuration.toMinutes() / 60));
         }
     }

     double totalMinutes = totalDuration.toMinutes();
     System.out.println("\n >>>>>>>> totalMinutes : " + totalMinutes);

     BigDecimal fractionalWorkingDays = BigDecimal.valueOf(totalMinutes)
         .divide(BigDecimal.valueOf(480), 4, RoundingMode.HALF_UP); // 480 minutes = 8 hours
     System.out.println("\n >>>>>>>> net workingDays : " + fractionalWorkingDays);

     double presentDays;
     double OTDays;

     if (fractionalWorkingDays.compareTo(new BigDecimal(totalDaysInMonth)) > 0) {
         presentDays = totalDaysInMonth;
         OTDays = fractionalWorkingDays.doubleValue() - totalDaysInMonth;
     } else {
         presentDays = fractionalWorkingDays.doubleValue();
         OTDays = 0;
     }

     System.out.println("\n >>>>>>>> presentDays : " + presentDays + "\t OTDays : " + OTDays);

     final double finalOTDays = OTDays;
     final double finalPresentDays = presentDays;

     List<EmployeeFinancialDTO> financialDTOs = employeeFinancialDAO
         .findByEmployeeEmployeeId(employeeId)
         .stream()
         .map(entity -> {
             EmployeeFinancialDTO dto = new EmployeeFinancialDTO(entity);

             BigDecimal otPerDay = BigDecimal.ZERO;
             BigDecimal empBasic = BigDecimal.ZERO;
             BigDecimal empDA = BigDecimal.ZERO;
             BigDecimal empHRA = BigDecimal.ZERO;
             BigDecimal empOther = BigDecimal.ZERO;
             BigDecimal overTime = BigDecimal.ZERO;

             //OT = OT/30
             
             if (dto.getEmpOTGross() != null) {
                 double gross = dto.getEmpOTGross().doubleValue();
                 double grossPerDay = gross / 30.0;
                 otPerDay = BigDecimal.valueOf(grossPerDay);
                 dto.setEmpOTGross(otPerDay);
                 System.out.println("empOTGross: " + gross + " | empOTGross / 30 = " + grossPerDay);
             }

             
             //Basic = Basic / TotalDays * PresentDays
             if (dto.getEmpBasic() != null) {
                 double basic = dto.getEmpBasic().doubleValue();
                 double basicCalculated = (basic / totalDaysInMonth) * finalPresentDays;
                 empBasic = BigDecimal.valueOf(basicCalculated);
                 dto.setEmpBasic(empBasic);
                 System.out.println("empBasic: " + basic + " | empBasic / " + totalDaysInMonth + " * " + finalPresentDays + " = " + basicCalculated);
             }

             
           //DA = DA / TotalDays * PresentDays
             if (dto.getEmpDA() != null) {
                 double da = dto.getEmpDA().doubleValue();
                 double daCalculated = (da / totalDaysInMonth) * finalPresentDays;
                 empDA = BigDecimal.valueOf(daCalculated);
                 dto.setEmpDA(empDA);
                 System.out.println("empDA: " + da + " | empDA / " + totalDaysInMonth + " * " + finalPresentDays + " = " + daCalculated);
             }

             //HRAAllownace = HRAAllownace / TotalDays * PresentDays
             if (dto.getEmpHRAAllowance() != null) {
                 double hra = dto.getEmpHRAAllowance().doubleValue();
                 double hraCalculated = (hra / totalDaysInMonth) * finalPresentDays;
                 empHRA = BigDecimal.valueOf(hraCalculated);
                 dto.setEmpHRAAllowance(empHRA);
                 System.out.println("empHRAAllowance: " + hra + " | empHRAAllowance / " + totalDaysInMonth + " * " + finalPresentDays + " = " + hraCalculated);
             }

             //OtherAllownace = OtherAllownace / TotalDays * PresentDays
             if (dto.getEmpOtherAllowance() != null) {
                 double other = dto.getEmpOtherAllowance().doubleValue();
                 double otherCalculated = (other / totalDaysInMonth) * finalPresentDays;
                 empOther = BigDecimal.valueOf(otherCalculated);
                 dto.setEmpOtherAllowance(empOther);
                 System.out.println("empOtherAllowance: " + other + " | empOtherAllowance / " + totalDaysInMonth + " * " + finalPresentDays + " = " + otherCalculated);
             }

             //Overtime = OTGROSS * OTDays
             overTime = otPerDay.multiply(BigDecimal.valueOf(finalOTDays)).setScale(2, RoundingMode.HALF_UP);
             dto.setOverTime(overTime);
             System.out.println("OverTime = " + otPerDay + " * " + finalOTDays + " = " + overTime);

             
             //Basic + DA + DiffDA
             BigDecimal empDiffDA = dto.getEmpDiffDAPreviousMonth() != null ? dto.getEmpDiffDAPreviousMonth() : BigDecimal.ZERO;
             BigDecimal basicDAWithDiffDA = empBasic.add(empDA).add(empDiffDA);
             dto.setBasicDAWithDiffDA(basicDAWithDiffDA);
             System.out.println("Basic+DA+DiffDA = " + empBasic + " + " + empDA + " + " + empDiffDA + " = " + basicDAWithDiffDA);

             
             //GROSS = (Basic + DA + DiffDA) + HRAAllowance + otherAllowance + OverTime
             BigDecimal gross = basicDAWithDiffDA.add(empHRA).add(empOther).add(overTime).setScale(2, RoundingMode.HALF_UP);
             dto.setGross(gross);
             System.out.println("Gross = " + basicDAWithDiffDA + " + " + empHRA + " + " + empOther + " + " + overTime + " = " + gross);

             //ProfessionalTax if the pfValue are > 15000 then it include for tax
             double pfValue = gross.doubleValue() - empHRA.doubleValue() - overTime.doubleValue();
             if (pfValue > 15000) {
                 dto.setEmpPFWages(new BigDecimal(15000));
             } else {
                 dto.setEmpPFWages(new BigDecimal(pfValue));
             }

             System.out.println("pfValue = " + gross + " - " + empHRA + " - " + overTime + " = " + pfValue);

             
             //PFWages = PFWages * 12 / 100
             BigDecimal empPFWages = dto.getEmpPFWages() != null ? dto.getEmpPFWages() : BigDecimal.ZERO;
             BigDecimal empProvidedFund = empPFWages.multiply(BigDecimal.valueOf(12)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
             dto.setEmpProvidedFund(empProvidedFund);
             System.out.println("empProvidedFund = " + empPFWages + " * 12 / 100 = " + empProvidedFund);

             if (dto.getEmpProfessionalTax() != null && dto.getEmpProfessionalTax().compareTo(BigDecimal.ONE) == 0) {
                 BigDecimal professionalTax = (month == 2)
                     ? BigDecimal.valueOf(300)
                     : BigDecimal.valueOf(200);
                 dto.setEmpProfessionalTax(professionalTax);
                 System.out.println("Professional Tax applied: " + professionalTax + " for month: " + month);
             } else {
                 dto.setEmpProfessionalTax(BigDecimal.ZERO);
                 System.out.println("Professional Tax not applicable (empProfessionalTax is 0)");
             }

             BigDecimal labour = dto.getLabour() != null ? dto.getLabour() : BigDecimal.ZERO;
             BigDecimal advance = dto.getAdvanceAmount() != null ? dto.getAdvanceAmount() : BigDecimal.ZERO;
             BigDecimal professionalTax = dto.getEmpProfessionalTax() != null ? dto.getEmpProfessionalTax() : BigDecimal.ZERO;
             BigDecimal pfFund = dto.getEmpProvidedFund() != null ? dto.getEmpProvidedFund() : BigDecimal.ZERO;

             
             //totalDeduction = PF+PT+Labout+Advance
             BigDecimal totalDeduction = pfFund.add(professionalTax).add(labour).add(advance).setScale(2, RoundingMode.HALF_UP);
             dto.setTotalDeduction(totalDeduction);
             System.out.println("totalDeduction = " + pfFund + " + " + professionalTax + " + " + labour + " + " + advance + " = " + totalDeduction);

             
             //netSalary = Gross - TotalDeduction
             BigDecimal netSalary = gross.subtract(totalDeduction).setScale(2, RoundingMode.HALF_UP);
             dto.setNetSalary(netSalary);
             System.out.println("netSalary = " + gross + " - " + totalDeduction + " = " + netSalary);

             return dto;
         })
         .collect(Collectors.toList());

     return new EmployeeFinancialWithPunchingResponse(
         financialDTOs,
         fractionalWorkingDays.doubleValue(),
         presentDays,
         OTDays
     );
 }

 
 
 @Override
 public Optional<EmployeeFinancialMaster> getLatestByEmployeeId(Integer employeeId) {
     return employeeFinancialDAO.findTopByEmployeeEmployeeIdOrderByEmpFinancialIdDesc(employeeId);
 }

 @Override
 public EmployeeFinancialMaster saveOrUpdate(EmployeeFinancialMaster financial) {
     return employeeFinancialDAO.save(financial);
 }



}