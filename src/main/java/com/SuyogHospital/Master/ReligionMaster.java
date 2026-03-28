package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblReligion")
public class ReligionMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer religionId;
	private String religiionName;
	private String religiionDescription;
	
	private Boolean isApproved;
	private String approvedBy;
	private String addedBy;
	private String updatedBy;
	private LocalDate date;
	private LocalTime time;
	public Integer getReligionId() {
		return religionId;
	}
	public void setReligionId(Integer religionId) {
		this.religionId = religionId;
	}
	public String getReligiionName() {
		return religiionName;
	}
	public void setReligiionName(String religiionName) {
		this.religiionName = religiionName;
	}
	public String getReligiionDescription() {
		return religiionDescription;
	}
	public void setReligiionDescription(String religiionDescription) {
		this.religiionDescription = religiionDescription;
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
}
