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
@Table(name = "tblAreaHistory")
public class AreaHistoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int areaId;
    private String areaName;
    private String areaDesc;
    private int cityId;

    private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;

    private String action;
    private LocalDateTime timestamp;

    public AreaHistoryMaster() {
        super();
    }

    public AreaHistoryMaster(Integer id, int areaId, String areaName, String areaDesc, int cityId,
                             Boolean isApproved, Boolean approvedBy, Boolean addedBy, Boolean updatedBy, 
                             LocalDate date, LocalTime time, String action, LocalDateTime timestamp) {
        super();
        this.id = id;
        this.areaId = areaId;
        this.areaName = areaName;
        this.areaDesc = areaDesc;
        this.cityId = cityId;
        this.isApproved = isApproved;
        this.approvedBy = approvedBy;
        this.addedBy = addedBy;
        this.updatedBy = updatedBy;
        this.date = date;
        this.time = time;
        this.action = action;
        this.timestamp = timestamp;
    }


    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
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
        return "AreaHistoryMaster [id=" + id + ", areaId=" + areaId + ", areaName=" + areaName 
                + ", areaDesc=" + areaDesc + ", cityId=" + cityId + ", isApproved=" + isApproved 
                + ", approvedBy=" + approvedBy + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy 
                + ", date=" + date + ", time=" + time + ", action=" + action + ", timestamp=" + timestamp + "]";
    }
}
