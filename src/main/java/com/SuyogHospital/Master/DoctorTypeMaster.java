package com.SuyogHospital.Master;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblDoctorType")
public class DoctorTypeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorTypeId;

    private String doctorTypeName;
    private String description;

    private Boolean addedBy;
    private Boolean updatedBy;

    private LocalDate date;
    private LocalTime time;

    public Integer getDoctorTypeId() {
        return doctorTypeId;
    }

    public void setDoctorTypeId(Integer doctorTypeId) {
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
}
