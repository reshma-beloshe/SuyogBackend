package com.SuyogHospital.Master;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Table(name = "tblAdvanceAllocation")
@Entity
public class AdvanceAllocationMaster {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer allocationId;

    private LocalDate advanceAmountDate;
    private BigDecimal amount;

    private BigDecimal ipd;
    private BigDecimal opd;
    private BigDecimal salaryAdvanced;
    private BigDecimal others;

    // ✅ New fields
    private BigDecimal recoveryFromSalary; // money deducted from salary to recover advance
    private BigDecimal balance; // running balance after each transaction

    @Transient
    private BigDecimal previousBalance;
    
    private Boolean approvedBy;
    private Boolean createdBy;
    private LocalDate date;
    private LocalTime time;
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private EmployeeMaster employee;

    // ✅ Helper to calculate total advances
    public BigDecimal calculateTotalAdvance() {
        BigDecimal total = BigDecimal.ZERO;
        total = total.add(ipd != null ? ipd : BigDecimal.ZERO);
        total = total.add(opd != null ? opd : BigDecimal.ZERO);
        total = total.add(salaryAdvanced != null ? salaryAdvanced : BigDecimal.ZERO);
        total = total.add(others != null ? others : BigDecimal.ZERO);
        return total;
    }

	public Integer getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(Integer allocationId) {
		this.allocationId = allocationId;
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

	public EmployeeMaster getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeMaster employee) {
		this.employee = employee;
	}

	public BigDecimal getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(BigDecimal previousBalance) {
		this.previousBalance = previousBalance;
	}
    
}
