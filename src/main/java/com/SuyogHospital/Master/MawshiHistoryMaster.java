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
@Table(name = "tblMawshiHistory")
public class MawshiHistoryMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer id;
	private int mawshiId;
	private String mawshiFirstName;
	private String mawshiLastName;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getMawshiId() {
		return mawshiId;
	}
	public void setMawshiId(int mawshiId) {
		this.mawshiId = mawshiId;
	}
	public String getMawshiFirstName() {
		return mawshiFirstName;
	}
	public void setMawshiFirstName(String mawshiFirstName) {
		this.mawshiFirstName = mawshiFirstName;
	}
	public String getMawshiLastName() {
		return mawshiLastName;
	}
	public void setMawshiLastName(String mawshiLastName) {
		this.mawshiLastName = mawshiLastName;
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
