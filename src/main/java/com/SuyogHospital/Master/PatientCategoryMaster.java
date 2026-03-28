package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblPatientCategory")
public class PatientCategoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientCategoryId;
    private String patientCategoryType;
    private String patientCategoryDescription;
    private String status;
    private LocalDate date;
    private LocalTime time;
    private Boolean isApproved;
    private String approvedBy;
    private Boolean isIndigent;
    private Boolean isWeaker;
    
    
	public Integer getPatientCategoryId() {
		return patientCategoryId;
	}
	public void setPatientCategoryId(Integer patientCategoryId) {
		this.patientCategoryId = patientCategoryId;
	}
	public String getPatientCategoryType() {
		return patientCategoryType;
	}
	public void setPatientCategoryType(String patientCategoryType) {
		this.patientCategoryType = patientCategoryType;
	}
	public String getPatientCategoryDescription() {
		return patientCategoryDescription;
	}
	public void setPatientCategoryDescription(String patientCategoryDescription) {
		this.patientCategoryDescription = patientCategoryDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Boolean getIsIndigent() {
		return isIndigent;
	}
	public void setIsIndigent(Boolean isIndigent) {
		this.isIndigent = isIndigent;
	}
	public Boolean getIsWeaker() {
		return isWeaker;
	}
	public void setIsWeaker(Boolean isWeaker) {
		this.isWeaker = isWeaker;
	}

}
