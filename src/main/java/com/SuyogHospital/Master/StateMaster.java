package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblState")
public class StateMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer stateId;
	private String stateName;
	private String stateDesc;
	private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;
    
    @ManyToOne
    @JoinColumn(name = "countryId", nullable = false)
    private CountryMaster country;

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
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

	public CountryMaster getCountry() {
		return country;
	}

	public void setCountry(CountryMaster country) {
		this.country = country;
	}

	public StateMaster() {
		super();
	}

	public StateMaster(Integer stateId, String stateName, String stateDesc, Boolean isApproved, Boolean approvedBy,
			Boolean addedBy, Boolean updatedBy, LocalDate date, LocalTime time, CountryMaster country) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.stateDesc = stateDesc;
		this.isApproved = isApproved;
		this.approvedBy = approvedBy;
		this.addedBy = addedBy;
		this.updatedBy = updatedBy;
		this.date = date;
		this.time = time;
		this.country = country;
	}

	@Override
	public String toString() {
		return "StateMaster [stateId=" + stateId + ", stateName=" + stateName + ", stateDesc=" + stateDesc
				+ ", isApproved=" + isApproved + ", approvedBy=" + approvedBy + ", addedBy=" + addedBy + ", updatedBy="
				+ updatedBy + ", date=" + date + ", time=" + time + ", country=" + country + "]";
	}

    
}
