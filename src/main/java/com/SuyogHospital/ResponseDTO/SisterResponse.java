package com.SuyogHospital.ResponseDTO;

public class SisterResponse {

    private String employeeName;
    private String shift;
    private String dutyDay;
    private String punchingInTime;
    private String dutyInTime;
    private String punchingOutTime;
    private String dutyOutTime;

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    public String getDutyDay() { return dutyDay; }
    public void setDutyDay(String dutyDay) { this.dutyDay = dutyDay; }

    public String getPunchingInTime() { return punchingInTime; }
    public void setPunchingInTime(String punchingInTime) { this.punchingInTime = punchingInTime; }

    public String getDutyInTime() { return dutyInTime; }
    public void setDutyInTime(String dutyInTime) { this.dutyInTime = dutyInTime; }

    public String getPunchingOutTime() { return punchingOutTime; }
    public void setPunchingOutTime(String punchingOutTime) { this.punchingOutTime = punchingOutTime; }

    public String getDutyOutTime() { return dutyOutTime; }
    public void setDutyOutTime(String dutyOutTime) { this.dutyOutTime = dutyOutTime; }

}
