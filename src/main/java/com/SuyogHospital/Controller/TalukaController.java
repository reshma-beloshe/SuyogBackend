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

import com.SuyogHospital.Master.DistrictMaster;
import com.SuyogHospital.Master.TalukaMaster;
import com.SuyogHospital.Service.DistrictService;
import com.SuyogHospital.Service.TalukaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Taluka")
public class TalukaController {

    @Autowired
    private TalukaService talukaService;

    @Autowired
    private DistrictService districtService;

    @PostMapping("/addTaluka")
    public ResponseEntity<String> addTaluka(@RequestBody @Valid TalukaMaster TalukaMaster) {
        DistrictMaster district = districtService.getDistrictService(TalukaMaster.getDistrict().getDistrictId());
        if (district != null) {
            TalukaMaster.setDistrict(district);
            Boolean isAdded = talukaService.addTalukaService(TalukaMaster);
            if (isAdded) {
                return new ResponseEntity<>("Taluka added successfully!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to add Taluka.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid district ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateTaluka/{talId}")
    public ResponseEntity<String> updateTaluka(
            @PathVariable("talId") @Positive(message = "Taluka ID must be positive") Integer talId,
            @RequestBody @Valid TalukaMaster TalukaMaster) {
        DistrictMaster district = districtService.getDistrictService(TalukaMaster.getDistrict().getDistrictId());
        if (district != null) {
            TalukaMaster.setDistrict(district);
            TalukaMaster.setTalId(talId);
            Boolean isUpdated = talukaService.updateTalukaService(TalukaMaster);
            if (isUpdated) {
                return new ResponseEntity<>("Taluka updated successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update Taluka.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid district ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteTaluka/{talId}")
    public ResponseEntity<String> deleteTaluka(@PathVariable("talId") @Positive(message = "Taluka ID must be positive") Integer talId) {
        TalukaMaster TalukaMaster = talukaService.getTalukaService(talId);
        if (TalukaMaster != null) {
            Boolean isDeleted = talukaService.deleteTalukaService(TalukaMaster);
            if (isDeleted) {
                return new ResponseEntity<>("Taluka deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete Taluka.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getTaluka/{talId}")
    public ResponseEntity<TalukaMaster> getTaluka(@PathVariable("talId") @Positive(message = "Taluka ID must be positive") Integer talId) {
        TalukaMaster TalukaMaster = talukaService.getTalukaService(talId);
        if (TalukaMaster != null) {
            return new ResponseEntity<>(TalukaMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllTalukas")
    public ResponseEntity<List<TalukaMaster>> getAllTalukas() {
        List<TalukaMaster> allTalukas = talukaService.getAllTalukaService();
        return new ResponseEntity<>(allTalukas, HttpStatus.OK);
    }
}
