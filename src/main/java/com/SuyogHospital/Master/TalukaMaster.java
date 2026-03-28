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
@Table(name = "tblTaluka")
public class TalukaMaster {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer talId;
    private String talName;
    private String talDesc;
    private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;
    
    @ManyToOne
    @JoinColumn(name = "districtId", nullable = false)
    private DistrictMaster district;

    public Integer getTalId() {
        return talId;
    }

    public void setTalId(Integer talId) {
        this.talId = talId;
    }

    public String getTalName() {
        return talName;
    }

    public void setTalName(String talName) {
        this.talName = talName;
    }

    public String getTalDesc() {
        return talDesc;
    }

    public void setTalDesc(String talDesc) {
        this.talDesc = talDesc;
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

    public DistrictMaster getDistrict() {
        return district;
    }

    public void setDistrict(DistrictMaster district) {
        this.district = district;
    }

    public TalukaMaster() {
        super();
    }

    public TalukaMaster(Integer talId, String talName, String talDesc, Boolean isApproved,
                     Boolean approvedBy, Boolean addedBy, Boolean updatedBy, LocalDate date, LocalTime time, DistrictMaster district) {
        super();
        this.talId = talId;
        this.talName = talName;
        this.talDesc = talDesc;
        this.isApproved = isApproved;
        this.approvedBy = approvedBy;
        this.addedBy = addedBy;
        this.updatedBy = updatedBy;
        this.date = date;
        this.time = time;
        this.district = district;
    }

    @Override
    public String toString() {
        return "TalMaster [talId=" + talId + ", talName=" + talName + ", talDesc=" + talDesc + ", isApproved=" + isApproved 
                + ", approvedBy=" + approvedBy + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy 
                + ", date=" + date + ", time=" + time + ", district=" + district + "]";
    }
}
