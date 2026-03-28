package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.ShiftMaster;

public interface ShiftService {

    public Boolean addShiftService(ShiftMaster shiftMaster);
    public Boolean updateShiftService(ShiftMaster shiftMaster);
    public Boolean deleteShiftService(ShiftMaster shiftMaster);
    public ShiftMaster getShiftService(Integer shiftId);
    public List<ShiftMaster> getAllShiftService();
}
