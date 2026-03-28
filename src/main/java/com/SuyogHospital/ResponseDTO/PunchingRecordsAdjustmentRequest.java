package com.SuyogHospital.ResponseDTO;

import java.time.LocalTime;

public class PunchingRecordsAdjustmentRequest {

    private LocalTime adjustmentHours;
    private String adjustmentRemarks;

	public LocalTime getAdjustmentHours() {
		return adjustmentHours;
	}
	public void setAdjustmentHours(LocalTime adjustmentHours) {
		this.adjustmentHours = adjustmentHours;
	}
	public String getAdjustmentRemarks() {
		return adjustmentRemarks;
	}
	public void setAdjustmentRemarks(String adjustmentRemarks) {
		this.adjustmentRemarks = adjustmentRemarks;
	}
}
