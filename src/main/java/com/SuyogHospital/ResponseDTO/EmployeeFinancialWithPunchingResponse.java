package com.SuyogHospital.ResponseDTO;

import java.util.List;

public class EmployeeFinancialWithPunchingResponse {

	private List<EmployeeFinancialDTO> financialRecords;
	private String employeeFirstName;
	private String employeeLastName;
	private double workingDays;
    private double presentDays;
    private double OTDays;
   

    
    public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public EmployeeFinancialWithPunchingResponse() {
        super();
    }

    public EmployeeFinancialWithPunchingResponse(List<EmployeeFinancialDTO> financialRecords, double workingDays, double presentDays, double OTDays) {
        this.financialRecords = financialRecords;
        this.workingDays = workingDays;
        this.presentDays = presentDays;
        this.OTDays = OTDays;
    }

    // Getters and Setters

    public List<EmployeeFinancialDTO> getFinancialRecords() {
        return financialRecords;
    }

    public void setFinancialRecords(List<EmployeeFinancialDTO> financialRecords) {
        this.financialRecords = financialRecords;
    }

    public double getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(double workingDays) {
        this.workingDays = workingDays;
    }

    public double getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(double presentDays) {
        this.presentDays = presentDays;
    }

    public double getOTDays() {
        return OTDays;
    }

    public void setOTDays(double oTDays) {
        OTDays = oTDays;
    }

    @Override
    public String toString() {
        return "EmployeeFinancialWithPunchingResponse [financialRecords=" + financialRecords 
            + ", workingDays=" + workingDays 
            + ", presentDays=" + presentDays 
            + ", OTDays=" + OTDays + "]";
    }
}
