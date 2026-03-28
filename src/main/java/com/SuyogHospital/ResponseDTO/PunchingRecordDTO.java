package com.SuyogHospital.ResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class PunchingRecordDTO {

	private String day;
    private LocalDate date;
    private String employeeName;
    private String shiftName;
    private LocalTime planedTime;
    private LocalTime punchingTime;
    private String totalTime;
    
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public LocalTime getPlanedTime() {
		return planedTime;
	}
	public void setPlanedTime(LocalTime planedTime) {
		this.planedTime = planedTime;
	}
	public LocalTime getPunchingTime() {
		return punchingTime;
	}
	public void setPunchingTime(LocalTime punchingTime) {
		this.punchingTime = punchingTime;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public PunchingRecordDTO() {
		super();
	}
	public PunchingRecordDTO(String day, LocalDate date, String employeeName, String shiftName, LocalTime planedTime,
			LocalTime punchingTime, String totalTime) {
		super();
		this.day = day;
		this.date = date;
		this.employeeName = employeeName;
		this.shiftName = shiftName;
		this.planedTime = planedTime;
		this.punchingTime = punchingTime;
		this.totalTime = totalTime;
	}
	@Override
	public String toString() {
		return "PunchingRecordDTO [day=" + day + ", date=" + date + ", employeeName=" + employeeName + ", shiftName="
				+ shiftName + ", planedTime=" + planedTime + ", punchingTime=" + punchingTime + ", totalTime="
				+ totalTime + "]";
	}
	
	public PunchingRecordDTO(LocalDate date, String day, String employeeName,
            String shiftName, LocalTime plannedTime,
            LocalTime punchingTime, String totalTime) {
		this.date = date;
		this.day = day;
		this.employeeName = employeeName;
		this.shiftName = shiftName;
		this.planedTime = planedTime;
		this.punchingTime = punchingTime;
		this.totalTime = totalTime;
}

    
}
