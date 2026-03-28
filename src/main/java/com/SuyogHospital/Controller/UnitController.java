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

import com.SuyogHospital.Master.UnitMaster;
import com.SuyogHospital.Service.UnitService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Unit")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @PostMapping("/addUnit")
    public ResponseEntity<String> addUnit(@RequestBody @Valid UnitMaster unitMaster) {
        Boolean isAdded = unitService.addUnitService(unitMaster);
        if (isAdded) {
            return new ResponseEntity<>("Unit added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add Unit.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateUnit/{unitId}")
    public ResponseEntity<String> updateUnit(
            @PathVariable("unitId") @Positive(message = "Unit ID must be positive") Integer unitId,
            @RequestBody @Valid UnitMaster unitMaster) {
        unitMaster.setUnitId(unitId);
        Boolean isUpdated = unitService.updateUnitService(unitMaster);
        if (isUpdated) {
            return new ResponseEntity<>("Unit updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Unit.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteUnit/{unitId}")
    public ResponseEntity<String> deleteUnit(@PathVariable("unitId") @Positive(message = "Unit ID must be positive") Integer unitId) {
        UnitMaster unitMaster = unitService.getUnitService(unitId);
        if (unitMaster != null) {
            Boolean isDeleted = unitService.deleteUnitService(unitMaster);
            if (isDeleted) {
                return new ResponseEntity<>("Unit deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete Unit.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getUnit/{unitId}")
    public ResponseEntity<UnitMaster> getUnit(@PathVariable("unitId") @Positive(message = "Unit ID must be positive") Integer unitId) {
        UnitMaster unitMaster = unitService.getUnitService(unitId);
        if (unitMaster != null) {
            return new ResponseEntity<>(unitMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllUnits")
    public ResponseEntity<List<UnitMaster>> getAllUnits() {
        List<UnitMaster> allUnits = unitService.getAllUnitService();
        return new ResponseEntity<>(allUnits, HttpStatus.OK);
    }
}
