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
@Table(name = "tblSisterHistory")
public class SisterHistoryMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer id;
	private int sisterId;
	private String sisterFirstName;
	private String sisterLastName;
	private String dutyDay;
	private LocalDate dutyDate;
	private LocalTime dutyInTime;
	private LocalTime dutyOutTime;
	
	private LocalDate date;
	private LocalTime time;
	
	private int shiftId;
	private int employeeId;
	
	private String action;
    private LocalDateTime timestamp;
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getSisterId() {
		return sisterId;
	}
	public void setSisterId(int sisterId) {
		this.sisterId = sisterId;
	}
	public String getSisterFirstName() {
		return sisterFirstName;
	}
	public void setSisterFirstName(String sisterFirstName) {
		this.sisterFirstName = sisterFirstName;
	}
	public String getSisterLastName() {
		return sisterLastName;
	}
	public void setSisterLastName(String sisterLastName) {
		this.sisterLastName = sisterLastName;
	}
	public String getDutyDay() {
		return dutyDay;
	}
	public void setDutyDay(String dutyDay) {
		this.dutyDay = dutyDay;
	}
	public LocalDate getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(LocalDate dutyDate) {
		this.dutyDate = dutyDate;
	}
	public LocalTime getDutyInTime() {
		return dutyInTime;
	}
	public void setDutyInTime(LocalTime dutyInTime) {
		this.dutyInTime = dutyInTime;
	}
	public LocalTime getDutyOutTime() {
		return dutyOutTime;
	}
	public void setDutyOutTime(LocalTime dutyOutTime) {
		this.dutyOutTime = dutyOutTime;
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
	
}
