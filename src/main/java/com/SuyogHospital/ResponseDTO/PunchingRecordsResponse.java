package com.SuyogHospital.ResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class PunchingRecordsResponse {

	private Integer punchingRecordId;

    private String day;	
    private LocalDate date;

    private String employeeFirstName;
    private String employeeLastName;
    private String shiftName;

    private LocalTime planedInTime;
    private LocalTime planedOutTime;
    private LocalTime punchingInTime;
    private LocalTime punchingOutTime;
    
    private LocalTime totalTime;
    
    private LocalTime adjustmentHours;
    private String adjustmentRemarks;

    private LocalTime netTotal;
	
}
