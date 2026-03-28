package com.SuyogHospital.Master;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblDepartment")
public class DepartmentMaster {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer departmentId;
    private String departmentName;
    private String description;
    private String nameOfHOD;
    private Boolean tarrifApplicable;
    private Boolean status;
    private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DepartmentMaster() {
        super();
    }

    public DepartmentMaster(Integer departmentId, String departmentName, String description, String nameOfHOD,
                            Boolean tarrifApplicable, Boolean status, Boolean isApproved, Boolean approvedBy,
                            Boolean addedBy, Boolean updatedBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super();
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
    public String toString() {
        return "DepartmentMaster [departmentId=" + departmentId + ", departmentName=" + departmentName
                + ", description=" + description + ", nameOfHOD=" + nameOfHOD + ", tarrifApplicable=" + tarrifApplicable
                + ", status=" + status + ", isApproved=" + isApproved + ", approvedBy=" + approvedBy 
                + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + "]";
    }
}
