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

@Entity
@Table(name = "tblEmployeeFinancial")
public class EmployeeFinancialMaster {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	
	private Integer empFinancialId;
    private BigDecimal empOTGross;
    private BigDecimal empBasic;
    private BigDecimal empDA;
    private BigDecimal empDiffDAPreviousMonth;
    private BigDecimal empHRAAllowance;
    private BigDecimal empOtherAllowance;
    private BigDecimal empProfessionalTax;
    private BigDecimal labour;
   
    private BigDecimal advanceAmount;

	private LocalDate date;
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private EmployeeMaster employee;

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

	public EmployeeMaster getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeMaster employee) {
		this.employee = employee;
	}

	public EmployeeFinancialMaster() {
		super();
	}

	public EmployeeFinancialMaster(Integer empFinancialId, BigDecimal empOTGross, BigDecimal empBasic, BigDecimal empDA,
			BigDecimal empDiffDAPreviousMonth, BigDecimal empHRAAllowance, BigDecimal empOtherAllowance,
			BigDecimal empProfessionalTax, BigDecimal labour, BigDecimal advanceAmount, LocalDate date, LocalTime time,
			EmployeeMaster employee) {
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
		this.date = date;
		this.time = time;
		this.employee = employee;
	}

	public EmployeeFinancialMaster(Integer empFinancialId2, Integer employeeId, LocalDate date2, BigDecimal empOTGross2,
			BigDecimal empBasic2, BigDecimal empDA2, BigDecimal empHRAAllowance2, BigDecimal empOtherAllowance2,
			BigDecimal empProfessionalTax2, BigDecimal labour2, BigDecimal advanceAmount2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeeFinancialMaster [empFinancialId=" + empFinancialId + ", empOTGross=" + empOTGross
				+ ", empBasic=" + empBasic + ", empDA=" + empDA + ", empDiffDAPreviousMonth=" + empDiffDAPreviousMonth
				+ ", empHRAAllowance=" + empHRAAllowance + ", empOtherAllowance=" + empOtherAllowance
				+ ", empProfessionalTax=" + empProfessionalTax + ", labour=" + labour + ", advanceAmount="
				+ advanceAmount + ", date=" + date + ", time=" + time + ", employee=" + employee + "]";
	}
}
