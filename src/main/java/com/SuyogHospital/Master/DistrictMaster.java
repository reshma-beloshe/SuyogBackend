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
@Table(name = "tblDistrict")
public class DistrictMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer districtId;
	private String districtName;
	private String districtDesc;
	private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;
    
    @ManyToOne
    @JoinColumn(name = "stateId", nullable = false)
    private StateMaster state;

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictDesc() {
		return districtDesc;
	}

	public void setDistrictDesc(String districtDesc) {
		this.districtDesc = districtDesc;
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

	public StateMaster getState() {
		return state;
	}

	public void setState(StateMaster state) {
		this.state = state;
	}

	public DistrictMaster() {
		super();
	}

	public DistrictMaster(Integer districtId, String districtName, String districtDesc, Boolean isApproved,
			Boolean approvedBy, Boolean addedBy, Boolean updatedBy, LocalDate date, LocalTime time, StateMaster state) {
		super();
		this.districtId = districtId;
		this.districtName = districtName;
		this.districtDesc = districtDesc;
		this.isApproved = isApproved;
		this.approvedBy = approvedBy;
		this.addedBy = addedBy;
		this.updatedBy = updatedBy;
		this.date = date;
		this.time = time;
		this.state = state;
	}

	@Override
	public String toString() {
		return "DistrictMaster [districtId=" + districtId + ", districtName=" + districtName + ", districtDesc="
				+ districtDesc + ", isApproved=" + isApproved + ", approvedBy=" + approvedBy + ", addedBy=" + addedBy
				+ ", updatedBy=" + updatedBy + ", date=" + date + ", time=" + time + ", state=" + state + "]";
	}
}
