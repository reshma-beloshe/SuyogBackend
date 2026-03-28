package com.SuyogHospital.Service;

import java.math.BigDecimal;
import java.util.List;

import com.SuyogHospital.Master.AdvanceAllocationMaster;

public interface AdvanceAllocationService {

	public Boolean addAdvanceAllocationService(AdvanceAllocationMaster advanceAllocationMaster);
    public Boolean updateAdvanceAllocationService(AdvanceAllocationMaster advanceAllocationMaster);
    public Boolean deleteAdvanceAllocationService(AdvanceAllocationMaster advanceAllocationMaster);
    public AdvanceAllocationMaster getAdvanceAllocationService(Integer advanceAllocationId);
    public List<AdvanceAllocationMaster> getAllAdvanceAllocationService();
    BigDecimal calculatePreviousBalance(Integer employeeId, Integer currentAllocationId);
	public double getPreviousBalanceByEmployee(Long employeeId);
}
