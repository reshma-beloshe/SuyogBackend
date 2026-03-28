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
@Table(name = "tblPunchingHistory")
public class PunchingHistoryMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer id;
	private int punchingId;
	private String punchingDay;
	private LocalDate punchingDate;
	private LocalTime inTime;
	private LocalTime outTime;
	private String approvedBy;
	private String addedBy;
	private String updatedBy;
	private LocalDate date;
	private LocalTime time;
	private int shiftId;
	private int employeeId;
	private String action;
	private LocalDateTime timestamp;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getPunchingId() {
		return punchingId;
	}
	public void setPunchingId(int punchingId) {
		this.punchingId = punchingId;
	}
	public String getPunchingDay() {
		return punchingDay;
	}
	public void setPunchingDay(String punchingDay) {
		this.punchingDay = punchingDay;
	}
	public LocalDate getPunchingDate() {
		return punchingDate;
	}
	public void setPunchingDate(LocalDate punchingDate) {
		this.punchingDate = punchingDate;
	}
	public LocalTime getInTime() {
		return inTime;
	}
	public void setInTime(LocalTime inTime) {
		this.inTime = inTime;
	}
	public LocalTime getOutTime() {
		return outTime;
	}
	public void setOutTime(LocalTime outTime) {
		this.outTime = outTime;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public int getShiftId() {
		return shiftId;
	}
	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
