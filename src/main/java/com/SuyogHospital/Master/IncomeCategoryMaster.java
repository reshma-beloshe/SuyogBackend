package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblIncomeCatagory")
public class IncomeCategoryMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer inComeId;
	private String incomeDesc;
	private String inComeFrom;
	private String inComeTo;
	
	private Boolean isApproved;
	private String approvedBy;
	private String addedBy;
	private String updatedBy;
	private LocalDate date;
	private LocalTime time;
	public Integer getInComeId() {
		return inComeId;
	}
	public void setInComeId(Integer inComeId) {
		this.inComeId = inComeId;
	}
	public String getIncomeDesc() {
		return incomeDesc;
	}
	public void setIncomeDesc(String incomeDesc) {
		this.incomeDesc = incomeDesc;
	}
	public String getInComeFrom() {
		return inComeFrom;
	}
	public void setInComeFrom(String inComeFrom) {
		this.inComeFrom = inComeFrom;
	}
	public String getInComeTo() {
		return inComeTo;
	}
	public void setInComeTo(String inComeTo) {
		this.inComeTo = inComeTo;
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
