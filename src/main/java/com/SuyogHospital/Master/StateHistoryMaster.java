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
@Table(name = "tblStateHistory")
public class StateHistoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int stateId;
    private String stateName;
    private String stateDesc;
    private int countryId;
    
    private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;

    private String action;

    private LocalDateTime timestamp;

    public StateHistoryMaster() {
        super();
    }

    public StateHistoryMaster(Integer id, String action) {
        this.id = id;
        this.action = action;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
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

	public StateHistoryMaster(Integer id, int stateId, String stateName, String stateDesc, int countryId,
			Boolean isApproved, Boolean approvedBy, Boolean addedBy, Boolean updatedBy, LocalDate date, LocalTime time,
			String action, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.stateId = stateId;
		this.stateName = stateName;
		this.stateDesc = stateDesc;
		this.countryId = countryId;
		this.isApproved = isApproved;
		this.approvedBy = approvedBy;
		this.addedBy = addedBy;
		this.updatedBy = updatedBy;
		this.date = date;
		this.time = time;
		this.action = action;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "StateHistoryMaster [id=" + id + ", stateId=" + stateId + ", stateName=" + stateName + ", stateDesc="
				+ stateDesc + ", countryId=" + countryId + ", isApproved=" + isApproved + ", approvedBy=" + approvedBy
				+ ", addedBy=" + addedBy + ", updatedBy=" + updatedBy + ", date=" + date + ", time=" + time
				+ ", action=" + action + ", timestamp=" + timestamp + "]";
	}

}
