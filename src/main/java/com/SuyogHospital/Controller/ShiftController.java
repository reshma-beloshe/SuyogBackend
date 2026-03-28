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

import com.SuyogHospital.Master.ShiftMaster;
import com.SuyogHospital.Service.ShiftService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Shift")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @PostMapping("/addShift")
    public ResponseEntity<String> addShift(@RequestBody @Valid ShiftMaster shiftMaster) {
        Boolean isAdded = shiftService.addShiftService(shiftMaster);
        if (isAdded) {
            return new ResponseEntity<>("Shift added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add Shift.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateShift/{shiftId}")
    public ResponseEntity<String> updateShift(
            @PathVariable("shiftId") @Positive(message = "Shift ID must be positive") Integer shiftId,
            @RequestBody @Valid ShiftMaster shiftMaster) {
        shiftMaster.setShiftId(shiftId);
        Boolean isUpdated = shiftService.updateShiftService(shiftMaster);
        if (isUpdated) {
            return new ResponseEntity<>("Shift updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Shift.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteShift/{shiftId}")
    public ResponseEntity<String> deleteShift(@PathVariable("shiftId") @Positive(message = "Shift ID must be positive") Integer shiftId) {
        ShiftMaster shiftMaster = shiftService.getShiftService(shiftId);
        if (shiftMaster != null) {
            Boolean isDeleted = shiftService.deleteShiftService(shiftMaster);
            if (isDeleted) {
                return new ResponseEntity<>("Shift deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete Shift.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getShift/{shiftId}")
    public ResponseEntity<ShiftMaster> getShift(@PathVariable("shiftId") @Positive(message = "Shift ID must be positive") Integer shiftId) {
        ShiftMaster shiftMaster = shiftService.getShiftService(shiftId);
        if (shiftMaster != null) {
            return new ResponseEntity<>(shiftMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllShifts")
    public ResponseEntity<List<ShiftMaster>> getAllShifts() {
        List<ShiftMaster> allShifts = shiftService.getAllShiftService();
        return new ResponseEntity<>(allShifts, HttpStatus.OK);
    }
}
