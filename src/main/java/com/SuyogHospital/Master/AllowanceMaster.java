package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblAllowance")
public class AllowanceMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer allowanceId;
	private String allowanceName;
	private String allowanceType;
	private String allowanceAmount;
	private String allowanceDescription;
	
	private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;
    
	public Integer getAllowanceId() {
		return allowanceId;
	}
	public void setAllowanceId(Integer allowanceId) {
		this.allowanceId = allowanceId;
	}
	public String getAllowanceName() {
		return allowanceName;
	}
	public void setAllowanceName(String allowanceName) {
		this.allowanceName = allowanceName;
	}
	public String getAllowanceType() {
		return allowanceType;
	}
	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}
	public String getAllowanceAmount() {
		return allowanceAmount;
	}
	public void setAllowanceAmount(String allowanceAmount) {
		this.allowanceAmount = allowanceAmount;
	}
	public String getAllowanceDescription() {
		return allowanceDescription;
	}
	public void setAllowanceDescription(String allowanceDescription) {
		this.allowanceDescription = allowanceDescription;
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
