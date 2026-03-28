package com.SuyogHospital.ResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class PunchingRecordEmpWiseReponse {

	 private String employeeFirstName;
	    private String employeeLastName;
	    private String day;
	    private LocalDate date;
	    private String shift;
	    private LocalTime plannedInTime;
	    private LocalTime punchingInTime;
	    private LocalTime plannedOutTime;
	    
	    
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
		public String getShift() {
			return shift;
		}
		public void setShift(String shift) {
			this.shift = shift;
		}
		public LocalTime getPlannedInTime() {
			return plannedInTime;
		}
		public void setPlannedInTime(LocalTime plannedInTime) {
			this.plannedInTime = plannedInTime;
		}
		public LocalTime getPunchingInTime() {
			return punchingInTime;
		}
		public void setPunchingInTime(LocalTime punchingInTime) {
			this.punchingInTime = punchingInTime;
		}
		public LocalTime getPlannedOutTime() {
			return plannedOutTime;
		}
		public void setPlannedOutTime(LocalTime plannedOutTime) {
			this.plannedOutTime = plannedOutTime;
		}
		public LocalTime getPunchingOutTime() {
			return punchingOutTime;
		}
		public void setPunchingOutTime(LocalTime punchingOutTime) {
			this.punchingOutTime = punchingOutTime;
		}
		public String getTotalTime() {
			return totalTime;
		}
		public void setTotalTime(String totalTime) {
			this.totalTime = totalTime;
		}
		private LocalTime punchingOutTime;
	    private String totalTime; 
	    
	    
}
