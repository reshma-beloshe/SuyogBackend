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
@Table(name = "tblArea")
public class AreaMaster {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer areaId;
    private String areaName;
    private String areaDesc;
    private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;
    
    
    
    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false)
    private CityMaster city;

    
    
    
    public AreaMaster() {
        super();
    }

    public AreaMaster(Integer areaId, String areaName, String areaDesc, Boolean isApproved,
                      Boolean approvedBy, Boolean addedBy, Boolean updatedBy, LocalDate date, LocalTime time, CityMaster city) {
        super();
        this.areaId = areaId;
        this.areaName = areaName;
        this.areaDesc = areaDesc;
        this.isApproved = isApproved;
        this.approvedBy = approvedBy;
        this.addedBy = addedBy;
        this.updatedBy = updatedBy;
        this.date = date;
        this.time = time;
        this.city = city;
    }


    public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
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

	public CityMaster getCity() {
		return city;
	}

	public void setCity(CityMaster city) {
		this.city = city;
	}

	@Override
    public String toString() {
        return "AreaMaster [areaId=" + areaId + ", areaName=" + areaName + ", areaDesc=" + areaDesc
                + ", isApproved=" + isApproved + ", approvedBy=" + approvedBy + ", addedBy=" + addedBy
                + ", updatedBy=" + updatedBy + ", date=" + date + ", time=" + time + ", city=" + city + "]";
    }
}
