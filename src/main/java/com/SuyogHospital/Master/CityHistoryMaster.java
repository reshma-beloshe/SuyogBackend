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
@Table(name = "tblCityHistory")
public class CityHistoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int cityId;
    private String cityName;
    private String cityDesc;
    private int talId;

    private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;

    private String action;
    private LocalDateTime timestamp;

    public CityHistoryMaster() {
        super();
    }

    public CityHistoryMaster(Integer id, int cityId, String cityName, String cityDesc, int talId,
                             Boolean isApproved, Boolean approvedBy, Boolean addedBy, Boolean updatedBy, 
                             LocalDate date, LocalTime time, String action, LocalDateTime timestamp) {
        super();
        this.id = id;
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityDesc = cityDesc;
        this.talId = talId;
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

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityDesc() {
		return cityDesc;
	}

	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}

	public int getTalId() {
		return talId;
	}

	public void setTalId(int talId) {
		this.talId = talId;
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
        return "CityHistoryMaster [id=" + id + ", cityId=" + cityId + ", cityName=" + cityName 
                + ", cityDesc=" + cityDesc + ", talId=" + talId + ", isApproved=" + isApproved 
                + ", approvedBy=" + approvedBy + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy 
                + ", date=" + date + ", time=" + time + ", action=" + action + ", timestamp=" + timestamp + "]";
    }
}
