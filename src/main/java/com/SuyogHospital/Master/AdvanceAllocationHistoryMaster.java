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
@Table(name = "tblAdvanceAllocationHistory")
public class AdvanceAllocationHistoryMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	 private Integer id;
	private int allocationId;
	private int employeeId;
	private LocalDate advanceAmountDate;
	private BigDecimal amount;
	private BigDecimal ipd;
	private BigDecimal opd;
	private BigDecimal salaryAdvanced;
	private BigDecimal others;
	 private BigDecimal recoveryFromSalary; // money deducted from salary to recover advance
	    private BigDecimal balance; // running balance after each transaction

	
    private Boolean approvedBy;
    private Boolean createdBy;
    private LocalDate date;
    private LocalTime time;
	private String remarks;
	 
	private String action;
    private LocalDateTime timestamp;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAllocationId() {
		return allocationId;
	}
	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public LocalDate getAdvanceAmountDate() {
		return advanceAmountDate;
	}
	public void setAdvanceAmountDate(LocalDate advanceAmountDate) {
		this.advanceAmountDate = advanceAmountDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getIpd() {
		return ipd;
	}
	public void setIpd(BigDecimal ipd) {
		this.ipd = ipd;
	}
	public BigDecimal getOpd() {
		return opd;
	}
	public void setOpd(BigDecimal opd) {
		this.opd = opd;
	}
	public BigDecimal getSalaryAdvanced() {
		return salaryAdvanced;
	}
	public void setSalaryAdvanced(BigDecimal salaryAdvanced) {
		this.salaryAdvanced = salaryAdvanced;
	}
	public BigDecimal getOthers() {
		return others;
	}
	public void setOthers(BigDecimal others) {
		this.others = others;
	}
	public Boolean getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(Boolean approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Boolean getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Boolean createdBy) {
		this.createdBy = createdBy;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public BigDecimal getRecoveryFromSalary() {
		return recoveryFromSalary;
	}
	public void setRecoveryFromSalary(BigDecimal recoveryFromSalary) {
		this.recoveryFromSalary = recoveryFromSalary;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
    
}
