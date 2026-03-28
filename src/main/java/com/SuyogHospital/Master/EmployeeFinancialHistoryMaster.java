package com.SuyogHospital.Master;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblEmployeeFinancialHistory")
public class EmployeeFinancialHistoryMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer id;
	private int empFinancialId;
    private BigDecimal empOTGross;
    private BigDecimal empBasic;
    private BigDecimal empDA;
    private BigDecimal empDiffDAPreviousMonth;
    private BigDecimal empHRAAllowance;
    private BigDecimal empOtherAllowance;
    private BigDecimal empProfessionalTax;
    private BigDecimal labour;

	private BigDecimal advanceAmount;
    private BigDecimal empPFWages;

    public BigDecimal getEmpPFWages() {
		return empPFWages;
	}
	public void setEmpPFWages(BigDecimal empPFWages) {
		this.empPFWages = empPFWages;
	}
	private int employeeId;
    private LocalDate date;
    private LocalTime time;
    
    private String action;
    private LocalDateTime timestamp;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getEmpFinancialId() {
		return empFinancialId;
	}
	public void setEmpFinancialId(int empFinancialId) {
		this.empFinancialId = empFinancialId;
	}
	public BigDecimal getEmpOTGross() {
		return empOTGross;
	}
	public void setEmpOTGross(BigDecimal empOTGross) {
		this.empOTGross = empOTGross;
	}
	public BigDecimal getEmpBasic() {
		return empBasic;
	}
	public void setEmpBasic(BigDecimal empBasic) {
		this.empBasic = empBasic;
	}
	public BigDecimal getEmpDA() {
		return empDA;
	}
	public void setEmpDA(BigDecimal empDA) {
		this.empDA = empDA;
	}
	public BigDecimal getEmpDiffDAPreviousMonth() {
		return empDiffDAPreviousMonth;
	}
	public void setEmpDiffDAPreviousMonth(BigDecimal empDiffDAPreviousMonth) {
		this.empDiffDAPreviousMonth = empDiffDAPreviousMonth;
	}
	public BigDecimal getEmpHRAAllowance() {
		return empHRAAllowance;
	}
	public void setEmpHRAAllowance(BigDecimal empHRAAllowance) {
		this.empHRAAllowance = empHRAAllowance;
	}
	public BigDecimal getEmpOtherAllowance() {
		return empOtherAllowance;
	}
	public void setEmpOtherAllowance(BigDecimal empOtherAllowance) {
		this.empOtherAllowance = empOtherAllowance;
	}
	public BigDecimal getEmpProfessionalTax() {
		return empProfessionalTax;
	}
	public void setEmpProfessionalTax(BigDecimal empProfessionalTax) {
		this.empProfessionalTax = empProfessionalTax;
	}
	public BigDecimal getLabour() {
		return labour;
	}
	public void setLabour(BigDecimal labour) {
		this.labour = labour;
	}
	public BigDecimal getAdvanceAmount() {
		return advanceAmount;
	}
	public void setAdvanceAmount(BigDecimal advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
