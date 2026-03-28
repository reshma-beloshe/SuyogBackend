package com.SuyogHospital.Master;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblUnitHistory")
public class UnitHistoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int unitId;
    private String unitName;
    private String description;
    private String abbreviation;
    private String opdNoPrefix;
    private String ipdNoPrefix;
    private Boolean status;
    private LocalDateTime applicableFrom;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;
    private String action;
    private LocalDateTime timestamp;

    public UnitHistoryMaster() {
        super();
    }

    public UnitHistoryMaster(Integer id, int unitId, String unitName, String description, String abbreviation,
                             String opdNoPrefix, String ipdNoPrefix, Boolean status, LocalDateTime applicableFrom,
                             Boolean approvedBy, Boolean addedBy, Boolean updatedBy, String action, LocalDateTime timestamp) {
        super();
        this.id = id;
        this.unitId = unitId;
        this.unitName = unitName;
        this.description = description;
        this.abbreviation = abbreviation;
        this.opdNoPrefix = opdNoPrefix;
        this.ipdNoPrefix = ipdNoPrefix;
        this.status = status;
        this.applicableFrom = applicableFrom;
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

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getOpdNoPrefix() {
		return opdNoPrefix;
	}

	public void setOpdNoPrefix(String opdNoPrefix) {
		this.opdNoPrefix = opdNoPrefix;
	}

	public String getIpdNoPrefix() {
		return ipdNoPrefix;
	}

	public void setIpdNoPrefix(String ipdNoPrefix) {
		this.ipdNoPrefix = ipdNoPrefix;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public LocalDateTime getApplicableFrom() {
		return applicableFrom;
	}

	public void setApplicableFrom(LocalDateTime applicableFrom) {
		this.applicableFrom = applicableFrom;
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
        return "UnitHistoryMaster [id=" + id + ", unitId=" + unitId + ", unitName=" + unitName + ", description=" + description
                + ", abbreviation=" + abbreviation + ", opdNoPrefix=" + opdNoPrefix + ", ipdNoPrefix=" + ipdNoPrefix
                + ", status=" + status + ", applicableFrom=" + applicableFrom + ", approvedBy=" + approvedBy
                + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy + ", action=" + action + ", timestamp=" + timestamp + "]";
    }
}
