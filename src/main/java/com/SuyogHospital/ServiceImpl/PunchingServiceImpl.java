package com.SuyogHospital.ServiceImpl;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.SuyogHospital.Master.EmployeeMaster;
import com.SuyogHospital.Master.MawshiMaster;
import com.SuyogHospital.Master.PunchingHistoryMaster;
import com.SuyogHospital.Master.PunchingMaster;
import com.SuyogHospital.Master.PunchingRecordsMaster;
import com.SuyogHospital.Master.ShiftMaster;
import com.SuyogHospital.Master.SisterMaster;
import com.SuyogHospital.Repository.EmployeeDAO;
import com.SuyogHospital.Repository.MawshiDAO;
import com.SuyogHospital.Repository.PunchingDAO;
import com.SuyogHospital.Repository.PunchingHistoryDAO;
import com.SuyogHospital.Repository.PunchingRecordsDAO;
import com.SuyogHospital.Repository.ShiftDAO;
import com.SuyogHospital.Repository.SisterDAO;
import com.SuyogHospital.ResponseDTO.PunchingRecordsAdjustmentRequest;
import com.SuyogHospital.Service.PunchingService;

@Service
@Transactional

public class PunchingServiceImpl implements PunchingService {

    private static final Logger logger = LogManager.getLogger(PunchingServiceImpl.class);

    @Autowired
    private PunchingDAO punchingDAO;

    @Autowired
    private PunchingHistoryDAO punchingHistoryDAO;

    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Autowired
    private ShiftDAO shiftDAO;
    
    @Autowired
    private PunchingRecordsDAO punchingRecordsDAO;
    
    @Autowired
    private SisterDAO sisterDAO;
    
    @Autowired
    private MawshiDAO mawshiDAO;
    
    @Override
    public Boolean addPunchingService(PunchingMaster punchingMaster) {
        try {
            logger.info("In addPunchingService");

            LocalDateTime now = LocalDateTime.now();
            punchingMaster.setDate(now.toLocalDate());
            punchingMaster.setTime(now.toLocalTime());
            punchingDAO.save(punchingMaster);

            PunchingHistoryMaster history = new PunchingHistoryMaster();
            history.setPunchingId(punchingMaster.getPunchingId());
            history.setPunchingDay(punchingMaster.getPunchingDay());
            history.setPunchingDate(punchingMaster.getPunchingDate());
            history.setInTime(punchingMaster.getInTime());
            history.setOutTime(punchingMaster.getOutTime());
            history.setApprovedBy(punchingMaster.getApprovedBy());
            history.setAddedBy(punchingMaster.getAddedBy());
            history.setUpdatedBy(punchingMaster.getUpdatedBy());
            // Corrected: Get IDs from the objects
            if (punchingMaster.getShift() != null) {
                history.setShiftId(punchingMaster.getShift().getShiftId());
            }
            if (punchingMaster.getEmployee() != null) {
                history.setEmployeeId(punchingMaster.getEmployee().getEmployeeId());
            }
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            punchingHistoryDAO.save(history);
            logger.info("Out of addPunchingService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updatePunchingService(PunchingMaster punchingMaster) {
        try {
            logger.info("In updatePunchingService");

            PunchingMaster existing = punchingDAO.findById(punchingMaster.getPunchingId())
                    .orElseThrow(() -> new RuntimeException("Punching record not found"));

            LocalDateTime now = LocalDateTime.now();

            PunchingHistoryMaster history = new PunchingHistoryMaster();
            history.setPunchingId(existing.getPunchingId());
            history.setPunchingDay(existing.getPunchingDay());
            history.setPunchingDate(existing.getPunchingDate());
            history.setInTime(existing.getInTime());
            history.setOutTime(existing.getOutTime());
            history.setApprovedBy(punchingMaster.getApprovedBy());
            history.setAddedBy(punchingMaster.getAddedBy());
            history.setUpdatedBy(punchingMaster.getUpdatedBy());
            // Corrected: Get IDs from the objects
            if (existing.getShift() != null) {
                history.setShiftId(existing.getShift().getShiftId());
            }
            if (existing.getEmployee() != null) {
                history.setEmployeeId(existing.getEmployee().getEmployeeId());
            }
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            existing.setPunchingDay(punchingMaster.getPunchingDay());
            existing.setPunchingDate(punchingMaster.getPunchingDate());
            existing.setInTime(punchingMaster.getInTime());
            existing.setOutTime(punchingMaster.getOutTime());
            existing.setApprovedBy(punchingMaster.getApprovedBy());
            existing.setAddedBy(punchingMaster.getAddedBy());
            existing.setUpdatedBy(punchingMaster.getUpdatedBy());
            existing.setShift(punchingMaster.getShift());
            existing.setEmployee(punchingMaster.getEmployee());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            punchingDAO.save(existing);
            punchingHistoryDAO.save(history);
            logger.info("Out of updatePunchingService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deletePunchingService(PunchingMaster punchingMaster) {
        try {
            logger.info("In deletePunchingService");

            PunchingMaster existing = punchingDAO.findById(punchingMaster.getPunchingId())
                    .orElseThrow(() -> new RuntimeException("Punching record not found"));

            PunchingHistoryMaster history = new PunchingHistoryMaster();
            history.setPunchingId(existing.getPunchingId());
            history.setPunchingDay(existing.getPunchingDay());
            history.setPunchingDate(existing.getPunchingDate());
            history.setInTime(existing.getInTime());
            history.setOutTime(existing.getOutTime());
            history.setApprovedBy(punchingMaster.getApprovedBy());
            history.setAddedBy(punchingMaster.getAddedBy());
            history.setUpdatedBy(punchingMaster.getUpdatedBy());
            // Corrected: Get IDs from the objects
            if (existing.getShift() != null) {
                history.setShiftId(existing.getShift().getShiftId());
            }
            if (existing.getEmployee() != null) {
                history.setEmployeeId(existing.getEmployee().getEmployeeId());
            }
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            punchingDAO.delete(existing);
            punchingHistoryDAO.save(history);
            logger.info("Out of deletePunchingService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public PunchingMaster getPunchingService(Integer punchingId) {
        try {
            logger.info("In getPunchingService");
            PunchingMaster punchingMaster = punchingDAO.findById(punchingId).orElse(null);
            logger.info("Out of getPunchingService");
            return punchingMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<PunchingMaster> getAllPunchingService() {
        List<PunchingMaster> allPunchings = new ArrayList<>();
        try {
            logger.info("In getAllPunchingService");
            allPunchings = (List<PunchingMaster>) punchingDAO.findAll();
            logger.info("Out of getAllPunchingService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allPunchings;
    }
    
    
    
    
    
    //Excel

    @Transactional(noRollbackFor = {NumberFormatException.class})
    @Override
    public void processPunchingExcel(MultipartFile file) throws Exception {
    	
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> in processPunchingExcel ...");
        Workbook workbook;

        if (file.getOriginalFilename().endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (file.getOriginalFilename().endsWith(".xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            throw new IllegalArgumentException("Only Excel files are supported (.xls or .xlsx)");
        }

        Sheet sheet = workbook.getSheetAt(0);
        LocalDate punchingDate = extractDateFromTopOfSheet(sheet);
        System.out.println("✅ Punching Date: " + punchingDate);

        int totalRows = 0;
        int successCount = 0;
        int failCount = 0;

        Cell empCodeCell = null;
        Cell empDeptCell = null;

        for (Row row : sheet) {
            if (row.getRowNum() < 10) continue;
            totalRows++;

            try {
                String strDept = getCellString(row.getCell(1));
                if (strDept != null && !strDept.isEmpty() && "Department ID:-".equals(strDept.trim()) && row.getCell(16) != null) {
                    empDeptCell = row.getCell(16);
                    System.out.println(">>>>>>>>>>🎯 Row : " + row.getRowNum() + "\t empDeptCell = " + empDeptCell);
                }

                empCodeCell = row.getCell(3);
                
               
                
                if (empCodeCell == null) {
                    logFailure(row, "Employee code cell is null. Skipping row.");
                    failCount++;
                    continue;
                }
                System.out.println(">>>>>>>>>>🎯 empCodeCell : " + empCodeCell.toString());
                String empCode = normalizeEmployeeCode(getCellString(empCodeCell));

                if (empCode.isEmpty() || empCode.equals("Empcode")) {
                    logFailure(row, "Empty employee code or Invalid EmpCode (likely header/empty row). Skipping row.");
                    failCount++;
                    continue;
                }

                EmployeeMaster employee = employeeDAO.findByEmployeeId(Integer.parseInt(empCode));
                if (employee == null) {
                    logFailure(row, "Employee not found for code: " + empCode + ". Skipping row.");
                    failCount++;
                    continue;
                }

                String shiftName = getCellString(row.getCell(10)).trim();
                ShiftMaster shift = shiftDAO.findByShiftNameIgnoreCase(shiftName);
                if (shift == null) {
                    logFailure(row, "Shift not found for name: '" + shiftName + "'. Skipping row.");
                    failCount++;
                    continue;
                }
                
                String inTimeStr = getCellString(row.getCell(12));
                String outTimeStr = getCellString(row.getCell(58));

                LocalTime inTime = parseFlexibleTime(inTimeStr);
                LocalTime outTime = parseFlexibleTime(outTimeStr);

                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> inTime \t " +inTime + "\t outTime:" + outTime );
                
                if (outTime == null) {
                    switch (shiftName.toUpperCase()) {
                        case "8AM TO 2PM":
                        case "A":
                            outTime = LocalTime.of(14, 0);
                            break;
                        case "2PM TO 8PM":
                        case "B":
                            outTime = LocalTime.of(20, 0);
                            break;
                        case "8PM TO 8AM":
                        case "C":
                            outTime = LocalTime.of(8, 0);
                            break;
                        default:
                            logFailure(row, "Unknown shift for default outTime. Shift: " + shiftName + ". Cannot assign OutTime.");
                            failCount++;
                            continue;
                    }
                    System.out.println("ℹ️ outTime was null. Defaulted to: " + outTime);
                }



                if (inTime == null) {
                    logFailure(row, "Invalid InTime format. InTime: " + inTimeStr + ". Skipping row.");
                    failCount++;
                    continue;
                }


                
                PunchingMaster punching = new PunchingMaster();
                punching.setEmployee(employee);
                punching.setShift(shift);
                punching.setPunchingDate(punchingDate);
                punching.setInTime(inTime);
                punching.setOutTime(outTime);
                punching.setPunchingDay(punchingDate.getDayOfWeek().name());
                punchingDAO.save(punching);

                PunchingRecordsMaster punchingRecord = new PunchingRecordsMaster();
                punchingRecord.setEmployee(employee);
                punchingRecord.setShift(shift);
                punchingRecord.setDate(punchingDate);
                punchingRecord.setDay(punchingDate.getDayOfWeek().name());
                punchingRecord.setPunchingInTime(inTime);
                punchingRecord.setPunchingOutTime(outTime);

                
                
                // === NEW LOGIC: Check for planned duty from Sister or Mawshi ===
                SisterMaster sister = sisterDAO.findByEmployeeAndDate(employee, punchingDate);
                MawshiMaster mawshi = mawshiDAO.findByEmployeeAndDate(employee, punchingDate);
                LocalTime planedInTime = LocalTime.of(8, 0); 
                LocalTime planedOutTime = LocalTime.of(14, 0);
                
                if (sister != null) {
                	planedInTime = sister.getDutyInTime();
                	planedOutTime = sister.getDutyOutTime();
                } else if (mawshi != null) {
                	planedInTime = mawshi.getDutyInTime();
                	planedOutTime = mawshi.getDutyOutTime();                    
                }
                
                punchingRecord.setPlanedInTime(planedInTime);
                punchingRecord.setPlanedOutTime(planedOutTime);
                
                //todo 
                
                
                    // 1. Determine actual start time (max of planedInTime and punchingInTime)
                    LocalTime actualStartTime = null;
                    if (planedInTime != null && inTime != null) {
                        actualStartTime = planedInTime.isAfter(inTime) ? planedInTime : inTime;
                    }
                    
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> inTime \t " +inTime + "\t outTime:" + outTime );

                    // 2. Determine actual end time (min of planedOutTime and punchingOutTime)
                    LocalTime actualEndTime = null;
                    if (planedOutTime != null && outTime != null) {
                        actualEndTime = planedOutTime.isBefore(outTime) ? planedOutTime : outTime;
                    }
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Empid : " + employee.getEmployeeFirstName()+ "\t planedInTime \t " +planedInTime + "\t actualStartTime:" + actualStartTime );

                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> planedOutTime \t " +planedOutTime + "\t actualEndTime:" + actualEndTime );

                
                    if (actualStartTime != null && actualEndTime != null ) {
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>planedOutTime \t 1");
                        Duration duration = null;
                        if(actualEndTime.isBefore(actualStartTime))
                            duration = Duration.between(actualEndTime, actualStartTime);
                        else 
                            duration = Duration.between(actualStartTime, actualEndTime);
                        
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>planedOutTime \t 2 : " + duration.toString());
                        long hours = duration.toHours();
                        long minutes = duration.toMinutesPart();
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>totalTime \t 3 : " + hours + ":" + minutes);
                        LocalTime totalTime = LocalTime.of((int) hours, (int) minutes);
                        punchingRecord.setTotalTime(totalTime);

                        // ✅ Add default adjustment fields
                        LocalTime adjustmentHours = LocalTime.of(0, 0);
                        String adjustmentRemarks = ""; // Empty by default

                        // Calculate netTotal
                        int totalMinutes = totalTime.getHour() * 60 + totalTime.getMinute();
                        int adjustmentMinutes = adjustmentHours.getHour() * 60 + adjustmentHours.getMinute();
                        int netMinutes = totalMinutes + adjustmentMinutes;
                        LocalTime netTotal = LocalTime.of(netMinutes / 60, netMinutes % 60);

                        // Set values
                        punchingRecord.setAdjustmentHours(adjustmentHours);
                        punchingRecord.setAdjustmentRemarks(adjustmentRemarks);
                        punchingRecord.setNetTotal(netTotal);


                    } else {
                        punchingRecord.setTotalTime(LocalTime.of(0, 0));
                        punchingRecord.setAdjustmentHours(LocalTime.of(0, 0));
                        punchingRecord.setAdjustmentRemarks("Punch-in or punch-out missing.");
                        punchingRecord.setNetTotal(LocalTime.of(0, 0));
                    }

                //TotalTime
//                Duration duration = Duration.between(inTime, outTime);
//                LocalTime totalTime = LocalTime.MIDNIGHT.plus(duration); // Converts Duration to LocalTime
//                punchingRecord.setTotalTime(totalTime);

                
               
                punchingRecordsDAO.save(punchingRecord);

                punchingDAO.flush();
                punchingRecordsDAO.flush();

                successCount++;

            } catch (NumberFormatException e) {
                failCount++;
                logFailure(row, "Invalid number format (e.g., employee code): " + e.getMessage());
                e.printStackTrace();
                throw e;
            } catch (Exception e) {
                failCount++;
                System.out.println("❌ Row " + row.getRowNum() + ": Failed with exception: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
        }

        try {
            workbook.close();
        } catch (IOException e) {
            System.err.println("Error closing workbook: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("🎯 Upload Summary: Total Rows Processed: " + totalRows + ", Success: " + successCount + ", Failed: " + failCount);
        if (successCount == 0 && totalRows > 0) {
            System.out.println("⚠ No data was uploaded due to errors or no valid entries.");
        } else if (successCount > 0) {
            System.out.println("🎯 Punching data upload complete.");
        } else {
            System.out.println("No rows found or processed in the specified range (10-64).");
        }
    }

    
    
    
    // Helper function to normalize employee code (remove leading zeros if any)
    private String normalizeEmployeeCode(String empCode) {
        empCode = empCode.trim();
        return empCode.replaceFirst("^0+(?!$)", ""); // remove leading zeros but keep "0" if it's the only character
    }

    // Shift lookup ignoring case
    // You must update ShiftDAO:
    // Add this method:  ShiftMaster findByShiftNameIgnoreCase(String shiftName);
    private ShiftMaster findShiftByName(String shiftName) {
        // This helper method is no longer strictly needed as the logic is in processPunchingExcel
        // but kept for completeness if you intend to use it elsewhere.
        return shiftDAO.findByShiftNameIgnoreCase(shiftName.trim());
    }

    // Flexible time parsing function
    private LocalTime parseFlexibleTime(String timeStr) {
        try {
            if (timeStr == null || timeStr.isEmpty()) return null;
            timeStr = timeStr.trim();

            // Handle "NULL" or other non-time indicators from Excel
            if (timeStr.equalsIgnoreCase("NULL") || timeStr.equalsIgnoreCase("N/A") || timeStr.equals("-") || timeStr.equals("--:--")) {
                return null;
            }

            // Try multiple patterns
            List<DateTimeFormatter> formatters = List.of(
                DateTimeFormatter.ofPattern("H:mm"),       // 9:30
                DateTimeFormatter.ofPattern("H:mm:ss"),   // 9:30:00
                DateTimeFormatter.ofPattern("HH:mm"),     // 09:30
                DateTimeFormatter.ofPattern("HHmm"),      // 0930 (no colon)
                DateTimeFormatter.ofPattern("Hmm")        // 930 (no colon)
            );

            // Try parsing directly with formatters
            for (DateTimeFormatter formatter : formatters) {
                try {
                    return LocalTime.parse(timeStr, formatter);
                } catch (Exception ignored) {
                    // Ignore and try next formatter
                }
            }

            // Try numeric (e.g. 930 -> 09:30, 1623 -> 16:23)
            // Ensure it's purely numeric before trying this
            if (timeStr.matches("^\\d{3,4}$")) { // Ensure it's 3 or 4 digits
                int timeInt = Integer.parseInt(timeStr);
                int hour = timeInt / 100;
                int minute = timeInt % 100;
                // Basic validation for hour and minute
                if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
                    return LocalTime.of(hour, minute);
                }
            }

            System.out.println("⚠ Cannot parse time: " + timeStr + " (No matching format found)");
            return null;

        } catch (Exception e) {
            // This catch block might indicate a serious issue if parseInt fails unexpectedly
            System.out.println("⚠ Error parsing time string '" + timeStr + "': " + e.getMessage());
            // Optionally, log this as a warning or error with your logging framework
            return null;
        }
    }

    private void logFailure(Row row, String message) {
        System.out.println("⚠ Row " + row.getRowNum() + ": " + message);
    }

    // Get Excel cell value as String
    private String getCellString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // For dates, return in a consistent format
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    return formatter.format(cell.getLocalDateTimeCellValue().toLocalDate());
                }
                // For general numbers, return as integer string if it looks like an integer
                if (cell.getNumericCellValue() == (long) cell.getNumericCellValue()) {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
                return String.valueOf(cell.getNumericCellValue()); // Fallback for decimals
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                // Try to evaluate formula, fall back to string if error
                try {
                    CellValue cellValue = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator().evaluate(cell);
                    if (cellValue.getCellType() == CellType.STRING) {
                        return cellValue.getStringValue().trim();
                    } else if (cellValue.getCellType() == CellType.NUMERIC) {
                         if (DateUtil.isCellDateFormatted(cell)) { // Check if original cell was date formatted
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            return formatter.format(cell.getLocalDateTimeCellValue().toLocalDate());
                        }
                        return String.valueOf((long) cellValue.getNumberValue());
                    }
                } catch (Exception e) {
                    System.err.println("Error evaluating formula in cell at row " + cell.getRowIndex() + ", col " + cell.getColumnIndex() + ": " + e.getMessage());
                }
                return ""; // Fallback for formula errors
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    private LocalDate extractDateFromTopOfSheet(Sheet sheet) {
        try {
            // Assuming date is in row 3 (index 2), column 11 (index 10)
            Row row = sheet.getRow(2);
            if (row == null) {
                throw new RuntimeException("Row 3 (index 2) not found for date extraction.");
            }
            Cell dateCell = row.getCell(10);
            if (dateCell == null) {
                throw new RuntimeException("Date cell (column 11/index 10) in row 3 is null.");
            }
            String dateStr = getCellString(dateCell);
            if (dateStr.isEmpty()) {
                 throw new RuntimeException("Date cell (column 11/index 10) in row 3 is empty.");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
            throw new RuntimeException("⚠ Invalid date format in Excel at row 3, column 11. Please ensure it's in dd/MM/yyyy format. Error: " + e.getMessage(), e);
        }
    }
    
    
    @Override
    public void updateAdjustment(Integer punchingRecordId, PunchingRecordsAdjustmentRequest dto) {
        PunchingRecordsMaster record = punchingRecordsDAO.findById(punchingRecordId)
                .orElseThrow(() -> new RuntimeException("Punching record not found with ID: " + punchingRecordId));

        LocalTime adjustmentHours = dto.getAdjustmentHours();
        String adjustmentRemarks = dto.getAdjustmentRemarks();

        record.setAdjustmentHours(adjustmentHours);
        record.setAdjustmentRemarks(adjustmentRemarks);

        LocalTime totalTime = record.getTotalTime();
        if (totalTime == null) totalTime = LocalTime.of(0, 0);

        int totalMinutes = totalTime.getHour() * 60 + totalTime.getMinute();
        int adjustmentMinutes = adjustmentHours.getHour() * 60 + adjustmentHours.getMinute();
        int netMinutes = totalMinutes + adjustmentMinutes;

        LocalTime netTotal = LocalTime.of(netMinutes / 60, netMinutes % 60);
        record.setNetTotal(netTotal);

        punchingRecordsDAO.save(record);
    }

    
    
    
    
    
    
    
    
    
    

    	
    @Override
    public List<PunchingRecordsMaster> getPunchingRecordsByEmployeeIdAndMonth(Integer employeeId, String month) {
        YearMonth yearMonth = YearMonth.parse(month); // e.g., "2025-07"
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        return punchingRecordsDAO.findByEmployee_EmployeeIdAndDateBetween(employeeId, startDate, endDate);
    }
}