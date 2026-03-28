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
@Table(name = "tblDoctorTypeHistory")
public class DoctorTypeHistoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int doctorTypeId;
    private String doctorTypeName;
    private String description;

    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;

    private String action;
    private LocalDateTime timestamp;

    public DoctorTypeHistoryMaster() {
        super();
    }

    public DoctorTypeHistoryMaster(Integer id, String action) {
        this.id = id;
        this.action = action;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDoctorTypeId() {
        return doctorTypeId;
    }

    public void setDoctorTypeId(int doctorTypeId) {
        this.doctorTypeId = doctorTypeId;
    }

    public String getDoctorTypeName() {
        return doctorTypeName;
    }

    public void setDoctorTypeName(String doctorTypeName) {
        this.doctorTypeName = doctorTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "DoctorTypeHistoryMaster [id=" + id + ", doctorTypeId=" + doctorTypeId + ", doctorTypeName=" + doctorTypeName
                + ", description=" + description + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy + ", date=" + date
                + ", time=" + time + ", action=" + action + ", timestamp=" + timestamp + "]";
    }
}
