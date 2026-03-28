package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblEmpCategory")
public class EmpCategoryMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer	empCategoryId;
	private String empCategoryName;
	private String empCategoryDescription;
	private String abbreviation;
	private String status;
	
	private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;
	public Integer getEmpCategoryId() {
		return empCategoryId;
	}
	public void setEmpCategoryId(Integer empCategoryId) {
		this.empCategoryId = empCategoryId;
	}
	public String getEmpCategoryName() {
		return empCategoryName;
	}
	public void setEmpCategoryName(String empCategoryName) {
		this.empCategoryName = empCategoryName;
	}
	public String getEmpCategoryDescription() {
		return empCategoryDescription;
	}
	public void setEmpCategoryDescription(String empCategoryDescription) {
		this.empCategoryDescription = empCategoryDescription;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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
