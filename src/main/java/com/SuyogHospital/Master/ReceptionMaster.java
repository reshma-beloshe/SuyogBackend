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
import jakarta.persistence.UniqueConstraint;

@Entity
//@Table(name = "tblReception")
@Table(uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"employee_id", "reception_date"})
	})
public class ReceptionMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer receptionId;
	private LocalDate receptionDate;
	private Integer month;
	private Integer year;
	private Boolean approvedBy;
    private Boolean createdBy;
    private LocalDate date;
    private LocalTime time;
	private String remarks;
	 
	 @ManyToOne
	 @JoinColumn(name = "employeeId", nullable = false)
	 private EmployeeMaster employee;
	 
	 @ManyToOne
	 @JoinColumn(name = "shiftId", nullable = false)
	 private ShiftMaster shift;

	public Integer getReceptionId() {
		return receptionId;
	}

	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
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

	public EmployeeMaster getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeMaster employee) {
		this.employee = employee;
	}

	public ShiftMaster getShift() {
		return shift;
	}

	public void setShift(ShiftMaster shift) {
		this.shift = shift;
	}
}
