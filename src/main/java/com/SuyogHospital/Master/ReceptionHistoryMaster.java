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
@Table(name = "tblReceptionHistory")
public class ReceptionHistoryMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private int receptionId;
	private int employeeId;
	private int shiftId;
	private LocalDate receptionDate;
	private Integer month;
	private Integer year;
	private Boolean approvedBy;
    private Boolean createdBy;
    private LocalDate date;
    private LocalTime time;
	private String remarks;

	private String action;
    private LocalDateTime timestamp;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getReceptionId() {
		return receptionId;
	}
	public void setReceptionId(int receptionId) {
		this.receptionId = receptionId;
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
	public LocalDate getReceptionDate() {
		return receptionDate;
	}
	public void setReceptionDate(LocalDate receptionDate) {
		this.receptionDate = receptionDate;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Boolean getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(Boolean approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Boolean getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Boolean createdBy) {
		this.createdBy = createdBy;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
