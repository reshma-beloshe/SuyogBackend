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
@Table(name = "tblMawshi")
public class MawshiMaster {


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer mawshiId;
	private String mawshiFirstName;
	private String mawshiLastName;
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

	public Integer getMawshiId() {
		return mawshiId;
	}

	public void setMawshiId(Integer mawshiId) {
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
