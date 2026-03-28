package com.SuyogHospital.ResponseDTO;

import java.math.BigDecimal;

public class AdvanceSummaryResponse {

    private String message;
    private BigDecimal ipd;
    private BigDecimal opd;
    private BigDecimal salaryAdvanced;
    private BigDecimal others;
    private BigDecimal total;
    private BigDecimal recoveryFromSalary;
    private BigDecimal previousBalance;
    private BigDecimal totalBalance;

    public AdvanceSummaryResponse(String message, BigDecimal ipd, BigDecimal opd, BigDecimal salaryAdvanced,
                                  BigDecimal others, BigDecimal total, BigDecimal recoveryFromSalary,
                                  BigDecimal previousBalance, BigDecimal totalBalance) {
        this.message = message;
        this.ipd = ipd;
        this.opd = opd;
        this.salaryAdvanced = salaryAdvanced;
        this.others = others;
        this.total = total;
        this.recoveryFromSalary = recoveryFromSalary;
        this.previousBalance = previousBalance;
        this.totalBalance = totalBalance;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getRecoveryFromSalary() {
		return recoveryFromSalary;
	}

	public void setRecoveryFromSalary(BigDecimal recoveryFromSalary) {
		this.recoveryFromSalary = recoveryFromSalary;
	}

	public BigDecimal getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(BigDecimal previousBalance) {
		this.previousBalance = previousBalance;
	}

	public BigDecimal getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}
}
