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
@Table(name = "tblPatientCategoryHistory")
public class PatientCategoryHistoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer patientCategoryId;
    private String patientCategoryType;
    private String patientCategoryDescription;
    private String status;
    private Boolean isApproved;
    private String approvedBy;
    private Boolean isIndigent;
    private Boolean isWeaker;
    private String action;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime timestamp;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
