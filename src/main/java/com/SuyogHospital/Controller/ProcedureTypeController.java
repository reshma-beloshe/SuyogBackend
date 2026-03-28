package com.SuyogHospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SuyogHospital.Master.ProcedureTypeMaster;
import com.SuyogHospital.Service.ProcedureTypeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/ProcedureType")
public class ProcedureTypeController {

    @Autowired
    private ProcedureTypeService procedureTypeService;

    @PostMapping("/addProcedureType")
    public ResponseEntity<String> addProcedureType(@RequestBody @Valid ProcedureTypeMaster procedureTypeMaster) {
        Boolean isAdded = procedureTypeService.addProcedureType(procedureTypeMaster);
        return isAdded ?
                new ResponseEntity<>("Procedure Type added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Procedure Type.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateProcedureType/{procedureTypeId}")
    public ResponseEntity<String> updateProcedureType(
            @PathVariable("procedureTypeId") @Positive Integer procedureTypeId,
            @RequestBody @Valid ProcedureTypeMaster procedureTypeMaster) {
        procedureTypeMaster.setProcedureTypeId(procedureTypeId);
        Boolean isUpdated = procedureTypeService.updateProcedureType(procedureTypeMaster);
        return isUpdated ?
                new ResponseEntity<>("Procedure Type updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Procedure Type.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteProcedureType/{procedureTypeId}")
    public ResponseEntity<String> deleteProcedureType(@PathVariable("procedureTypeId") @Positive Integer procedureTypeId) {
        ProcedureTypeMaster procedureTypeMaster = procedureTypeService.getProcedureType(procedureTypeId);
        return procedureTypeMaster != null && procedureTypeService.deleteProcedureType(procedureTypeMaster) ?
                new ResponseEntity<>("Procedure Type deleted successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to delete Procedure Type.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getProcedureType/{procedureTypeId}")
    public ResponseEntity<ProcedureTypeMaster> getProcedureType(@PathVariable("procedureTypeId") @Positive Integer procedureTypeId) {
        return new ResponseEntity<>(procedureTypeService.getProcedureType(procedureTypeId), HttpStatus.OK);
    }

    @GetMapping("/getAllProcedureTypes")
    public ResponseEntity<List<ProcedureTypeMaster>> getAllProcedureTypes() {
        return new ResponseEntity<>(procedureTypeService.getAllProcedureTypes(), HttpStatus.OK);
    }
}
