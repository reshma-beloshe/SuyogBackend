package com.SuyogHospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SuyogHospital.ResponseDTO.PunchingRecordsResponse;
import com.SuyogHospital.Service.PunchingRecordsService;

@RestController
@RequestMapping("/PunchingRecords")
public class PunchingRecordsController {

    @Autowired
    private PunchingRecordsService punchingRecordsService;

    @GetMapping("/getAllPunchingRecords")
    public ResponseEntity<List<PunchingRecordsResponse>> getAllPunchingRecords() {
        List<PunchingRecordsResponse> response = punchingRecordsService.getAllPunchingRecords();
        return ResponseEntity.ok(response);
    }

    
    
    
    
    
    
    
    
    
    
    
    
//    @GetMapping("/{id}")
//    public PunchingRecordsMaster getById(@PathVariable Integer id) {
//        return service.getRecordById(id);
//    }
//
//    @PostMapping
//    public PunchingRecordsMaster create(@RequestBody PunchingRecordsMaster record) {
//        return service.createRecord(record);
//    }
//
//    @PutMapping("/{id}")
//    public PunchingRecordsMaster update(@PathVariable Integer id, @RequestBody PunchingRecordsMaster record) {
//        return service.updateRecord(id, record);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Integer	 id) {
//        service.deleteRecord(id);
//    }
//    
//    
//    @GetMapping("/{employeeId}/{month}")
//    public ApiResponse<List<PunchingRecordDTO>> getPunchingRecords(@PathVariable("employeeId") Integer employeeId, @PathVariable("month") String month) {
//        List<PunchingRecordDTO> data = service.getPunchingRecords(employeeId, month);
//        if (data.isEmpty()) {
//            return new ApiResponse<>("success", "No records found for this employee and month.", data);
//        }
//        return new ApiResponse<>("success", "Records found successfully.", data);
//    }

}
