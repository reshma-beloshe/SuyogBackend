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
@Table(name = "tblFloorHistory")
public class FloorHistoryMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private int floorId;
	private String floorDescription;
	private String floorStatus;
	private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;

    private String action;
    private LocalDateTime timestamp;
    
    public FloorHistoryMaster() {
        super();
    }

    public FloorHistoryMaster(Integer id, String action) {
        this.id = id;
        this.action = action;
    }
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getFloorId() {
		return floorId;
	}
	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}
	public String getFloorDescription() {
		return floorDescription;
	}
	public void setFloorDescription(String floorDescription) {
		this.floorDescription = floorDescription;
	}
	public String getFloorStatus() {
		return floorStatus;
	}
	public void setFloorStatus(String floorStatus) {
		this.floorStatus = floorStatus;
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
    
    
}

