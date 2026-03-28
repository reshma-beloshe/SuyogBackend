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
@Table(name = "tblWard")
public class WardMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer wardId;
	private String wardDescription;
	private String wardStatus;
	private String sex;
	
	private Boolean isApproved;
	private Boolean approvedBy;
	private Boolean addedBy;
	private Boolean updatedBy;

	private LocalDate date;
	private LocalTime time;
	    
	@ManyToOne
	@JoinColumn(name = "floorId", nullable = false)
	private FloorMaster floor;
	
	@ManyToOne
	@JoinColumn(name = "blockId", nullable = false)
	private BlockMaster block;

	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public String getWardDescription() {
		return wardDescription;
	}

	public void setWardDescription(String wardDescription) {
		this.wardDescription = wardDescription;
	}

	public String getWardStatus() {
		return wardStatus;
	}

	public void setWardStatus(String wardStatus) {
		this.wardStatus = wardStatus;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public FloorMaster getFloor() {
		return floor;
	}

	public void setFloor(FloorMaster floor) {
		this.floor = floor;
	}

	public BlockMaster getBlock() {
		return block;
	}

	public void setBlock(BlockMaster block) {
		this.block = block;
	}


}
