package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblTarrifHistory")
public class TarrifHistoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tarrifId;
    private String tarrifType;
    private String tarrifDescription;
    private String status;
    private String linkWithService;
    private String plusMinus;
    private Double percentage;
    private Boolean isApproved;
    private Boolean approvedBy;
    private Boolean addedBy;
    private Boolean updatedBy;
    private LocalDate date;
    private LocalTime time;
    private String action;
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getTarrifId() { return tarrifId; }
    public void setTarrifId(Integer tarrifId) { this.tarrifId = tarrifId; }

    public String getTarrifType() { return tarrifType; }
    public void setTarrifType(String tarrifType) { this.tarrifType = tarrifType; }

    public String getTarrifDescription() { return tarrifDescription; }
    public void setTarrifDescription(String tarrifDescription) { this.tarrifDescription = tarrifDescription; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLinkWithService() { return linkWithService; }
    public void setLinkWithService(String linkWithService) { this.linkWithService = linkWithService; }

    public String getPlusMinus() { return plusMinus; }
    public void setPlusMinus(String plusMinus) { this.plusMinus = plusMinus; }

    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }

    public Boolean getIsApproved() { return isApproved; }
    public void setIsApproved(Boolean isApproved) { this.isApproved = isApproved; }

    public Boolean getApprovedBy() { return approvedBy; }
    public void setApprovedBy(Boolean approvedBy) { this.approvedBy = approvedBy; }

    public Boolean getAddedBy() { return addedBy; }
    public void setAddedBy(Boolean addedBy) { this.addedBy = addedBy; }

    public Boolean getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(Boolean updatedBy) { this.updatedBy = updatedBy; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
