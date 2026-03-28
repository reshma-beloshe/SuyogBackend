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
@Table(name = "tblPunchingRecords")
public class PunchingRecordsMaster {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer punchingRecordId;

	    private String day;	
	    private LocalDate date;

	    @ManyToOne
	    @JoinColumn(name = "employee_id")
	    private EmployeeMaster employee;

	    @ManyToOne
	    @JoinColumn(name = "shift_id")
	    private ShiftMaster shift;

	    private LocalTime planedInTime;
	    private LocalTime planedOutTime;
	    private LocalTime punchingInTime;
	    private LocalTime punchingOutTime;
	    
	    private LocalTime totalTime;
	    
	    private LocalTime adjustmentHours;
	    private String adjustmentRemarks;

	    private LocalTime netTotal;


		public Integer getPunchingRecordId() {
			return punchingRecordId;
		}

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

		public LocalTime getTotalTime() {
			return totalTime;
		}

		public void setTotalTime(LocalTime totalTime) {
			this.totalTime = totalTime;
		}

	
	    
}
