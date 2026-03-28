package com.SuyogHospital.ServiceImpl;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.PunchingRecordsMaster;
import com.SuyogHospital.Repository.EmployeeDAO;
import com.SuyogHospital.Repository.MawshiDAO;
import com.SuyogHospital.Repository.PunchingDAO;
import com.SuyogHospital.Repository.PunchingRecordsDAO;
import com.SuyogHospital.Repository.SisterDAO;
import com.SuyogHospital.ResponseDTO.PunchingRecordsResponse;
import com.SuyogHospital.Service.PunchingRecordsService;

@Service
public class PunchingRecordsServiceImpl implements PunchingRecordsService {

    @Autowired
    private PunchingRecordsDAO punchingRecordsDAO;
    
    @Autowired
    private SisterDAO sisterDAO;

    @Autowired
    private MawshiDAO mawshiDAO;

    @Autowired
    private PunchingDAO punchingDAO;

    @Autowired
    private EmployeeDAO employeeDAO;


    @Override
    public List<PunchingRecordsResponse> getAllPunchingRecords() {
        Iterable<PunchingRecordsMaster> recordsIterable = punchingRecordsDAO.findAll();

        List<PunchingRecordsMaster> records = StreamSupport.stream(recordsIterable.spliterator(), false)
                .collect(Collectors.toList());

        return records.stream().map(p -> {
            PunchingRecordsResponse dto = new PunchingRecordsResponse();
            dto.setPunchingRecordId(p.getPunchingRecordId());
            dto.setDay(p.getDay());
            dto.setDate(p.getDate());

            if (p.getEmployee() != null) {
                dto.setEmployeeFirstName(p.getEmployee().getEmployeeFirstName());
                dto.setEmployeeLastName(p.getEmployee().getEmployeeLastName());
            }

            if (p.getShift() != null) {
                dto.setShiftName(p.getShift().getShiftName());
            }

            dto.setPlanedInTime(p.getPlanedInTime());
            dto.setPunchingInTime(p.getPunchingInTime());
            dto.setPlanedOutTime(p.getPlanedOutTime());
            dto.setPunchingOutTime(p.getPunchingOutTime());

            dto.setTotalTime(p.getTotalTime());

            // ✅ Newly added fields
            dto.setAdjustmentHours(p.getAdjustmentHours());
            dto.setAdjustmentRemarks(p.getAdjustmentRemarks());
            dto.setNetTotal(p.getNetTotal());

            return dto;
        }).collect(Collectors.toList());
}
}

//
//// 1. Determine actual start time (max of planedInTime and punchingInTime)
//LocalTime actualStartTime = null;
//if (planedInTime != null && punchingInTime != null) {
//  actualStartTime = planedInTime.isAfter(punchingInTime) ? planedInTime : punchingInTime;
//}
//
//// 2. Determine actual end time (min of planedOutTime and punchingOutTime)
//LocalTime actualEndTime = null;
//if (planedOutTime != null && punchingOutTime != null) {
//  actualEndTime = planedOutTime.isBefore(punchingOutTime) ? planedOutTime : punchingOutTime;
//}
//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> planedInTime \t " +planedInTime + "\t actualStartTime:" + actualStartTime );
//
//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>planedOutTime \t" +planedOutTime + " \t actualEndTime : " +actualEndTime );
//


//// 3. Set total time as the duration between actual start and end times
//
//if (actualStartTime != null && actualEndTime != null && !actualEndTime.isBefore(actualStartTime)) {
//	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>planedOutTime \t 1");
//  Duration duration = Duration.between(actualStartTime, actualEndTime);
//  System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>planedOutTime \t 2 : " +duration.toString());
//  long hours = duration.toHours();
//  long minutes = duration.toMinutesPart();
//  System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>totalTime \t 3 : " +hours +":" +minutes);
//  LocalTime totalTime = LocalTime.of((int) hours, (int) minutes);
//  		
//  p.setTotalTime(totalTime); // ✅ This sets it as LocalTime
//  dto.setTotalTime(totalTime);
//} else {
//  p.setTotalTime(LocalTime.of(0, 0)); // or set to null if you prefer
//  dto.setTotalTime(LocalTime.of(0, 0));
//}
//
