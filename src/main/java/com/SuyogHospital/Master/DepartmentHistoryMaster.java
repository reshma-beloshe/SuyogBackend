package com.SuyogHospital.Master;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblDepartmentHistory")
public class DepartmentHistoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int departmentId;
    private String departmentName;
    private String description;
    private String nameOfHOD;
    private Boolean tarrifApplicable;
    private Boolean status;
    private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;
    private String action;
    private LocalDateTime timestamp;

    public DepartmentHistoryMaster() {
        super();
    }

    public DepartmentHistoryMaster(Integer id, int departmentId, String departmentName, String description, String nameOfHOD,
                                   Boolean tarrifApplicable, Boolean status, Boolean isApproved, Boolean approvedBy,
                                   Boolean addedBy, Boolean updatedBy, String action, LocalDateTime timestamp) {
        super();
        this.id = id;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.description = description;
        this.nameOfHOD = nameOfHOD;
        this.tarrifApplicable = tarrifApplicable;
        this.status = status;
        this.isApproved = isApproved;
        this.approvedBy = approvedBy;
        this.addedBy = addedBy;
        this.updatedBy = updatedBy;
        this.action = action;
        this.timestamp = timestamp;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameOfHOD() {
		return nameOfHOD;
	}

	public void setNameOfHOD(String nameOfHOD) {
		this.nameOfHOD = nameOfHOD;
	}

	public Boolean getTarrifApplicable() {
		return tarrifApplicable;
	}

	public void setTarrifApplicable(Boolean tarrifApplicable) {
		this.tarrifApplicable = tarrifApplicable;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
    public String toString() {
        return "DepartmentHistoryMaster [id=" + id + ", departmentId=" + departmentId + ", departmentName=" + departmentName 
                + ", description=" + description + ", nameOfHOD=" + nameOfHOD + ", tarrifApplicable=" + tarrifApplicable
                + ", status=" + status + ", isApproved=" + isApproved + ", approvedBy=" + approvedBy 
                + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy + ", action=" + action + ", timestamp=" + timestamp + "]";
    }
}
