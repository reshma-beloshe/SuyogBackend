package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.ResponseDTO.PunchingRecordsResponse;

public interface PunchingRecordsService {

    List<PunchingRecordsResponse> getAllPunchingRecords();

//    PunchingRecordsMaster getRecordById(Integer id);
//
//    PunchingRecordsMaster createRecord(PunchingRecordsMaster record);
//
//    PunchingRecordsMaster updateRecord(Integer id, PunchingRecordsMaster updatedRecord);
//
//    void deleteRecord(Integer id);
//    
//    List<PunchingRecordDTO> getPunchingRecords(Integer employeeId, String month);

}
