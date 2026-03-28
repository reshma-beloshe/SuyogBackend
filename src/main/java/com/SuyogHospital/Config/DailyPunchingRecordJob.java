//package com.SuyogHospital.Config;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.SuyogHospital.Master.EmployeeMaster;
//import com.SuyogHospital.Master.MawshiMaster;
//import com.SuyogHospital.Master.PunchingMaster;
//import com.SuyogHospital.Master.PunchingRecordsMaster;
//import com.SuyogHospital.Master.ShiftMaster;
//import com.SuyogHospital.Master.SisterMaster;
//import com.SuyogHospital.Repository.MawshiDAO;
//import com.SuyogHospital.Repository.PunchingDAO;
//import com.SuyogHospital.Repository.PunchingRecordsDAO;
//import com.SuyogHospital.Repository.SisterDAO;
//
//@Component
//public class DailyPunchingRecordJob {
//
//    @Autowired
//    private SisterDAO sisterMasterRepository;
//
//    @Autowired
//    private MawshiDAO mawshiMasterRepository;
//
//    @Autowired
//    private PunchingDAO punchingMasterRepository;
//
//    @Autowired
//    private PunchingRecordsDAO punchingRecordsMasterRepository;
//
//    
//    @Scheduled(cron = "0 0 1 * * *") // Every day at 1:00 AM
//    public void generateDailyPunchingRecords() {
//        LocalDate today = LocalDate.now();
//        String todayDay = today.getDayOfWeek().name();
//
//        // Get all planned duty entries for today
//        List<SisterMaster> sisterPlans = sisterMasterRepository.findByDate(today);
//        List<MawshiMaster> mawshiPlans = mawshiMasterRepository.findByDate(today);
//
//        // Process Sister duty entries
//        for (SisterMaster plan : sisterPlans) {
//            Integer empId = plan.getEmployee().getEmployeeId();
//            PunchingMaster punch = punchingMasterRepository.findByEmployee_EmployeeIdAndDate(empId, today);
//
//            if (punch != null) {
//                createPunchingRecord(today, todayDay, plan.getEmployee(), plan.getShift(), plan.getDutyInTime(), punch.getPunchingInTime());
//            }
//        }
//
//        // Process Mawshi duty entries
//        for (MawshiMaster plan : mawshiPlans) {
//            Integer empId = plan.getEmployee().getEmployeeId();
//            PunchingMaster punch = punchingMasterRepository.findByEmployee_EmployeeIdAndDate(empId, today);
//
//            if (punch != null) {
//                createPunchingRecord(today, todayDay, plan.getEmployee(), plan.getShift(), plan.getDutyInTime(), punch.getPunchingInTime());
//            }
//        }
//    }
//
//    /**
//     * Create and save a PunchingRecordsMaster entry
//     */
//    private void createPunchingRecord(LocalDate date, String day, EmployeeMaster emp, ShiftMaster shift,
//                                      LocalTime planedTime, LocalTime punchingTime) {
//
//        PunchingRecordsMaster record = new PunchingRecordsMaster();
//        record.setDate(date);
//        record.setDay(day);
//        record.setEmployee(emp);
//        record.setShift(shift);
//        record.setPlanedTime(planedTime);
//        record.setPunchingTime(punchingTime);
//        record.setTotalTime(calculateTotalTime(planedTime, punchingTime));
//
//        punchingRecordsMasterRepository.save(record);
//    }
//
//    /**
//     * Calculates the difference between planned and actual punching time
//     */
//    private String calculateTotalTime(LocalTime planed, LocalTime actual) {
//        Duration duration = Duration.between(planed, actual);
//        long hours = Math.abs(duration.toHours());
//        long minutes = Math.abs(duration.toMinutesPart());
//        return String.format("%02d:%02d", hours, minutes);
//    }
//}
//
