package com.SuyogHospital.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.SuyogHospital.Master.PunchingMaster;
import com.SuyogHospital.Master.PunchingRecordsMaster;
import com.SuyogHospital.ResponseDTO.PunchingRecordsAdjustmentRequest;

public interface PunchingService {

    public Boolean addPunchingService(PunchingMaster punchingMaster);
    public Boolean updatePunchingService(PunchingMaster punchingMaster);
    public Boolean deletePunchingService(PunchingMaster punchingMaster);
    public PunchingMaster getPunchingService(Integer punchingId);
    public List<PunchingMaster> getAllPunchingService();
    
    void processPunchingExcel(MultipartFile file) throws Exception;
    List<PunchingRecordsMaster> getPunchingRecordsByEmployeeIdAndMonth(Integer employeeId, String month);

    public void updateAdjustment(Integer punchingRecordId, PunchingRecordsAdjustmentRequest dto);

}
