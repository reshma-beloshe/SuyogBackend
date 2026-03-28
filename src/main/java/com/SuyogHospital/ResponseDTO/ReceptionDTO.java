package com.SuyogHospital.ResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReceptionDTO {

	private Integer receptionId;
    private Integer employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private Integer shiftId;
    private String shiftName;
    private LocalDate receptionDate;
    private Integer month;
    private Integer year;
    private LocalDate date;
    private LocalTime time;
    private String remarks;
    
	public Integer getReceptionId() {
		return receptionId;
	}
	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public Integer getShiftId() {
		return shiftId;
	}
	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
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
}
