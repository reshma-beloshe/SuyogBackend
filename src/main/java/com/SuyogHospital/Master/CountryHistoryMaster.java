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
@Table(name = "tblCountryHistory")
public class CountryHistoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int countryId;
    private String countryName;
    private String countryDesc;

    private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;
    
    private String action;

    private LocalDateTime timestamp;

    public CountryHistoryMaster() {
        super();
    }

    public CountryHistoryMaster(Integer id, String action) {
        this.id = id;
        this.action = action;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryDesc() {
		return countryDesc;
	}

	public void setCountryDesc(String countryDesc) {
		this.countryDesc = countryDesc;
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

	public CountryHistoryMaster(Integer id, int countryId, String countryName, String countryDesc, Boolean isApproved,
			Boolean approvedBy, Boolean addedBy, Boolean updatedBy, LocalDate date, LocalTime time, String action,
			LocalDateTime timestamp) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryDesc = countryDesc;
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
		return "CountryHistoryMaster [id=" + id + ", countryId=" + countryId + ", countryName=" + countryName
				+ ", countryDesc=" + countryDesc + ", isApproved=" + isApproved + ", approvedBy=" + approvedBy
				+ ", addedBy=" + addedBy + ", updatedBy=" + updatedBy + ", date=" + date + ", time=" + time
				+ ", action=" + action + ", timestamp=" + timestamp + "]";
	}

    
}
