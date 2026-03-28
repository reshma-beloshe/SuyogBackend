package com.SuyogHospital.ResponseDTO;

import java.math.BigDecimal;

import com.SuyogHospital.Master.EmployeeFinancialMaster;

public class EmployeeFinancialDTO {

	private Integer empFinancialId;
    private BigDecimal empOTGross;
    private BigDecimal empBasic;
    private BigDecimal empDA;
    private BigDecimal empDiffDAPreviousMonth;
    private BigDecimal basicDAWithDiffDA;
    private BigDecimal empHRAAllowance;
    private BigDecimal empOtherAllowance;
    private BigDecimal empProfessionalTax;
    private BigDecimal labour;
    
    private BigDecimal ipd;
    private BigDecimal opd;
    private BigDecimal salaryAdvanced;
    private BigDecimal others;

    
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

	private BigDecimal advanceAmount;
    private BigDecimal overTime;
    private BigDecimal gross;
    private BigDecimal empPFWages;
    private BigDecimal empProvidedFund;
    private BigDecimal totalDeduction;
    private BigDecimal netSalary;
    

    public BigDecimal getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(BigDecimal netSalary) {
		this.netSalary = netSalary;
	}

	public BigDecimal getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(BigDecimal totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

	public BigDecimal getEmpProvidedFund() {
		return empProvidedFund;
	}

	public void setEmpProvidedFund(BigDecimal empProvidedFund) {
		this.empProvidedFund = empProvidedFund;
	}

	public BigDecimal getGross() {
		return gross;
	}

	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}

	// Constructor
    public EmployeeFinancialDTO(EmployeeFinancialMaster financial) {
        this.empFinancialId = financial.getEmpFinancialId();
        this.empOTGross = financial.getEmpOTGross();
        this.empBasic = financial.getEmpBasic();
        this.empDA = financial.getEmpDA();
        this.empDiffDAPreviousMonth = financial.getEmpDiffDAPreviousMonth();
        this.empHRAAllowance = financial.getEmpHRAAllowance();
        this.empOtherAllowance = financial.getEmpOtherAllowance();
        this.empProfessionalTax = financial.getEmpProfessionalTax();
        this.labour = financial.getLabour();
        this.advanceAmount = financial.getAdvanceAmount();
    }

	public Integer getEmpFinancialId() {
		return empFinancialId;
	}

	public void setEmpFinancialId(Integer empFinancialId) {
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

	public BigDecimal getOverTime() {
	    return overTime;
	}

	public void setOverTime(BigDecimal overTime) {
	    this.overTime = overTime;
	}
	
	
	
	public BigDecimal getBasicDAWithDiffDA() {
		return basicDAWithDiffDA;
	}

	public void setBasicDAWithDiffDA(BigDecimal basicDAWithDiffDA) {
		this.basicDAWithDiffDA = basicDAWithDiffDA;
	}

	public BigDecimal getEmpPFWages() {
		return empPFWages;
	}

	public void setEmpPFWages(BigDecimal empPFWages) {
		this.empPFWages = empPFWages;
	}

	public EmployeeFinancialDTO() {
		super();
	}

	public EmployeeFinancialDTO(Integer empFinancialId, BigDecimal empOTGross, BigDecimal empBasic, BigDecimal empDA,
			BigDecimal empDiffDAPreviousMonth, BigDecimal empHRAAllowance, BigDecimal empOtherAllowance,
			BigDecimal empProfessionalTax, BigDecimal labour, BigDecimal advanceAmount) {
		super();
		this.empFinancialId = empFinancialId;
		this.empOTGross = empOTGross;
		this.empBasic = empBasic;
		this.empDA = empDA;
		this.empDiffDAPreviousMonth = empDiffDAPreviousMonth;
		this.empHRAAllowance = empHRAAllowance;
		this.empOtherAllowance = empOtherAllowance;
		this.empProfessionalTax = empProfessionalTax;
		this.labour = labour;
		this.advanceAmount = advanceAmount;
	}

	@Override
	public String toString() {
		return "EmployeeFinancialDTO [empFinancialId=" + empFinancialId + ", empOTGross=" + empOTGross + ", empBasic="
				+ empBasic + ", empDA=" + empDA + ", empDiffDAPreviousMonth=" + empDiffDAPreviousMonth
				+ ", empHRAAllowance=" + empHRAAllowance + ", empOtherAllowance=" + empOtherAllowance
				+ ", empProfessionalTax=" + empProfessionalTax + ", labour=" + labour + ", advanceAmount="
				+ advanceAmount + "]";
	}
}
