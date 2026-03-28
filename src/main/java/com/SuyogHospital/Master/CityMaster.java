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
@Table(name = "tblCity")
public class CityMaster {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer cityId;
    private String cityName;
    private String cityDesc;
    private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;
    
    @ManyToOne
    @JoinColumn(name = "talId", nullable = false)
    private TalukaMaster taluka;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
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

	public TalukaMaster getTaluka() {
		return taluka;
	}

	public void setTaluka(TalukaMaster taluka) {
		this.taluka = taluka;
	}

	public CityMaster() {
		super();
	}

	public CityMaster(Integer cityId, String cityName, String cityDesc, Boolean isApproved, Boolean approvedBy,
			Boolean addedBy, Boolean updatedBy, LocalDate date, LocalTime time, TalukaMaster taluka) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.cityDesc = cityDesc;
		this.isApproved = isApproved;
		this.approvedBy = approvedBy;
		this.addedBy = addedBy;
		this.updatedBy = updatedBy;
		this.date = date;
		this.time = time;
		this.taluka = taluka;
	}

	@Override
	public String toString() {
		return "CityMaster [cityId=" + cityId + ", cityName=" + cityName + ", cityDesc=" + cityDesc + ", isApproved="
				+ isApproved + ", approvedBy=" + approvedBy + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy
				+ ", date=" + date + ", time=" + time + ", taluka=" + taluka + "]";
	}
}
