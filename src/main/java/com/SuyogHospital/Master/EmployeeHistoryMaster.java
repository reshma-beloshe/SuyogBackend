package com.SuyogHospital.Master;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblEmployeeHistory")
public class EmployeeHistoryMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer id;
	private int employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeCode;
	private String gender;
	private LocalDate birthDate;
	private String mobileNo;
	private String emailId;
	private LocalDate dateOfJoining;
	   
	private String status;
	private LocalDate date;
	private LocalTime time;
	private boolean isApproved;
	private String approvedBy;
	private String bankAcNo;
	private String acType;
	private String salaryMode;
	private String panNo;
	private String lAddress;
	private String lPin;
	private String pAddress;
	private String pPin;
	private BigDecimal advanceAmount;
	
	
	public BigDecimal getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(BigDecimal advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	private int departmentId;
	private int designationId;
	private int empCategoryId;
	private int areaId;
	private int bankId;
	
	private String action;
    private LocalDateTime timestamp;
    
    @ElementCollection
    @CollectionTable(name = "employee_history_allowance", joinColumns = @JoinColumn(name = "employee_history_id"))
    @Column(name = "allowance_id")
    private List<Integer> allowanceIds;

    @ElementCollection
    @CollectionTable(name = "employee_history_deduction", joinColumns = @JoinColumn(name = "employee_history_id"))
    @Column(name = "deduction_id")
    private List<Integer> deductionIds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getBankAcNo() {
		return bankAcNo;
	}

	public void setBankAcNo(String bankAcNo) {
		this.bankAcNo = bankAcNo;
	}

	public String getAcType() {
		return acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	public String getSalaryMode() {
		return salaryMode;
	}

	public void setSalaryMode(String salaryMode) {
		this.salaryMode = salaryMode;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getlAddress() {
		return lAddress;
	}

	public void setlAddress(String lAddress) {
		this.lAddress = lAddress;
	}

	public String getlPin() {
		return lPin;
	}

	public void setlPin(String lPin) {
		this.lPin = lPin;
	}

	public String getpAddress() {
		return pAddress;
	}

	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}

	public String getpPin() {
		return pPin;
	}

	public void setpPin(String pPin) {
		this.pPin = pPin;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public int getEmpCategoryId() {
		return empCategoryId;
	}

	public void setEmpCategoryId(int empCategoryId) {
		this.empCategoryId = empCategoryId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
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

	public List<Integer> getAllowanceIds() {
		return allowanceIds;
	}

	public void setAllowanceIds(List<Integer> allowanceIds) {
		this.allowanceIds = allowanceIds;
	}

	public List<Integer> getDeductionIds() {
		return deductionIds;
	}

	public void setDeductionIds(List<Integer> deductionIds) {
		this.deductionIds = deductionIds;
	}

}
