package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblPunchingRecordsHistory")
public class PunchingRecordsHistoryMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer id;
	
	  private int punchingRecordId;

	  private String day;	
	  private LocalDate date;
	  private int employeeId;
	  private int shiftId;
	  private LocalTime planedInTime;
	    private LocalTime planedOutTime;
	    private LocalTime punchingInTime;
	    private LocalTime punchingOutTime;
	  private String totalTime;
	  private LocalTime adjustmentHours;
	    private String adjustmentRemarks;
	    
		private LocalTime netTotal;
	  
		
		
	  public LocalTime getAdjustmentHours() {
			return adjustmentHours;
		}
		public void setAdjustmentHours(LocalTime adjustmentHours) {
			this.adjustmentHours = adjustmentHours;
		}
		public String getAdjustmentRemarks() {
			return adjustmentRemarks;
		}
		public void setAdjustmentRemarks(String adjustmentRemarks) {
			this.adjustmentRemarks = adjustmentRemarks;
		}
		public LocalTime getNetTotal() {
			return netTotal;
		}
		public void setNetTotal(LocalTime netTotal) {
			this.netTotal = netTotal;
		}
	private String action;
	  private LocalDateTime timestamp;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPunchingRecordId() {
		return punchingRecordId;
	}
	public void setPunchingRecordId(Integer punchingRecordId) {
		this.punchingRecordId = punchingRecordId;
	}
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
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getShiftId() {
		return shiftId;
	}
	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}
	public LocalTime getPlanedInTime() {
		return planedInTime;
	}
	public void setPlanedInTime(LocalTime planedInTime) {
		this.planedInTime = planedInTime;
	}
	public LocalTime getPlanedOutTime() {
		return planedOutTime;
	}
	public void setPlanedOutTime(LocalTime planedOutTime) {
		this.planedOutTime = planedOutTime;
	}
	public LocalTime getPunchingInTime() {
		return punchingInTime;
	}
	public void setPunchingInTime(LocalTime punchingInTime) {
		this.punchingInTime = punchingInTime;
	}
	public LocalTime getPunchingOutTime() {
		return punchingOutTime;
	}
	public void setPunchingOutTime(LocalTime punchingOutTime) {
		this.punchingOutTime = punchingOutTime;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	  
}
