package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblProcedureType")
public class ProcedureTypeMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer procedureTypeId;
	private String procedureTypeName;
	private String procedureTypeDescription;
	private String procedureTypeStatus;
	private String idSurgical;
	
	private Boolean isApproved;
	private String approvedBy;
	private String addedBy;
	private String updatedBy;
	private LocalDate date;
	private LocalTime time;
	public Integer getProcedureTypeId() {
		return procedureTypeId;
	}
	public void setProcedureTypeId(Integer procedureTypeId) {
		this.procedureTypeId = procedureTypeId;
	}
	public String getProcedureTypeName() {
		return procedureTypeName;
	}
	public void setProcedureTypeName(String procedureTypeName) {
		this.procedureTypeName = procedureTypeName;
	}
	public String getProcedureTypeDescription() {
		return procedureTypeDescription;
	}
	public void setProcedureTypeDescription(String procedureTypeDescription) {
		this.procedureTypeDescription = procedureTypeDescription;
	}
	public String getProcedureTypeStatus() {
		return procedureTypeStatus;
	}
	public void setProcedureTypeStatus(String procedureTypeStatus) {
		this.procedureTypeStatus = procedureTypeStatus;
	}
	public String getIdSurgical() {
		return idSurgical;
	}
	public void setIdSurgical(String idSurgical) {
		this.idSurgical = idSurgical;
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
