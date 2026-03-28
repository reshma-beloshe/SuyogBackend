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
@Table(name = "tblPunching")
public class PunchingMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer punchingId;
	private String punchingDay;
	private LocalDate punchingDate;
	private LocalTime inTime;
	private LocalTime outTime;
	private String approvedBy;
	private String addedBy;
	private String updatedBy;
	private LocalDate date;
	private LocalTime time;
	
	@ManyToOne
    @JoinColumn(name = "shiftId", nullable = false)
    private ShiftMaster shift;
	
	@ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private EmployeeMaster employee;

	public Integer getPunchingId() {
		return punchingId;
	}

	public void setPunchingId(Integer punchingId) {
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

	public LocalTime getPunchingInTime() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
