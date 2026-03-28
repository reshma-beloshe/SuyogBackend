package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblSister")
public class SisterMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer sisterId;
	private String sisterFirstName;
	private String sisterLastName;
	private String dutyDay;
	private LocalDate dutyDate;
	private LocalTime dutyInTime;
	private LocalTime dutyOutTime;
	
	private LocalDate date;
	private LocalTime time;
	
	@ManyToOne
    @JoinColumn(name = "shiftId", nullable = false)
    private ShiftMaster shift;
	
	@ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private EmployeeMaster employee;

	public Integer getSisterId() {
		return sisterId;
	}

	public void setSisterId(Integer sisterId) {
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

	public ShiftMaster getShift() {
		return shift;
	}

	public void setShift(ShiftMaster shift) {
		this.shift = shift;
	}

	public EmployeeMaster getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeMaster employee) {
		this.employee = employee;
	}
	
}
