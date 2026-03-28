package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblSpecialisation")
public class SpecialisationMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer specialisationId;
    private String specialisationType;
    private String description;
    private String status;
    private LocalDate applicableFrom;
    private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;
    private LocalDate date;
    private LocalTime time;
	public Integer getSpecialisationId() {
		return specialisationId;
	}
	public void setSpecialisationId(Integer specialisationId) {
		this.specialisationId = specialisationId;
	}
	public String getSpecialisationType() {
		return specialisationType;
	}
	public void setSpecialisationType(String specialisationType) {
		this.specialisationType = specialisationType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getApplicableFrom() {
		return applicableFrom;
	}
	public void setApplicableFrom(LocalDate applicableFrom) {
		this.applicableFrom = applicableFrom;
	}
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	public Boolean getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(Boolean approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Boolean getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(Boolean addedBy) {
		this.addedBy = addedBy;
	}
	public Boolean getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Boolean updatedBy) {
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


}
