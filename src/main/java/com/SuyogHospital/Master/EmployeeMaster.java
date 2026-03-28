package com.SuyogHospital.Master;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblEmployee")
public class EmployeeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
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
    
	@ManyToOne
    @JoinColumn(name = "departmentId")
    private DepartmentMaster department;

    @ManyToOne
    @JoinColumn(name = "designationId")
    private DesignationMaster designation;

    @ManyToOne
    @JoinColumn(name = "empCategoryId")
    private EmpCategoryMaster empCategory;
    
	@ManyToOne
    @JoinColumn(name = "areaId")
    private AreaMaster area;
    
    @ManyToOne
    @JoinColumn(name = "bankId")
    private BankMaster bank;

    
    @ManyToMany
    @JoinTable(
        name = "employee_allowance",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "allowance_id")
    )
    private List<AllowanceMaster> allowances;

    // ✅ NEW: Many-to-many for Deductions
    @ManyToMany
    @JoinTable(
        name = "employee_deduction",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "deduction_id")
    )
    private List<DeductionMaster> deductions;

    

    public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
    
    public BigDecimal getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(BigDecimal advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
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

	public DepartmentMaster getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentMaster department) {
		this.department = department;
	}

	public DesignationMaster getDesignation() {
		return designation;
	}

	public void setDesignation(DesignationMaster designation) {
		this.designation = designation;
	}

	public EmpCategoryMaster getEmpCategory() {
		return empCategory;
	}

	public void setEmpCategory(EmpCategoryMaster empCategory) {
		this.empCategory = empCategory;
	}

	public AreaMaster getArea() {
		return area;
	}

	public void setArea(AreaMaster area) {
		this.area = area;
	}

	public BankMaster getBank() {
		return bank;
	}

	public void setBank(BankMaster bank) {
		this.bank = bank;
	}

	public List<AllowanceMaster> getAllowances() {
		return allowances;
	}

	public void setAllowances(List<AllowanceMaster> allowances) {
		this.allowances = allowances;
	}

	public List<DeductionMaster> getDeductions() {
		return deductions;
	}

	public void setDeductions(List<DeductionMaster> deductions) {
		this.deductions = deductions;
	}
    
}
