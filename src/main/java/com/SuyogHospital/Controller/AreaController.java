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

import com.SuyogHospital.Master.AreaMaster;
import com.SuyogHospital.Master.CityMaster;
import com.SuyogHospital.Service.AreaService;
import com.SuyogHospital.Service.CityService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private CityService cityService;

    @PostMapping("/addArea")
    public ResponseEntity<String> addArea(@RequestBody @Valid AreaMaster areaMaster) {
        CityMaster city = cityService.getCityService(areaMaster.getCity().getCityId());
        if (city != null) {
            areaMaster.setCity(city);
            Boolean isAdded = areaService.addAreaService(areaMaster);
            if (isAdded) {
                return new ResponseEntity<>("Area added successfully!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to add Area.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid city ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateArea/{areaId}")
    public ResponseEntity<String> updateArea(
            @PathVariable("areaId") @Positive(message = "Area ID must be positive") Integer areaId,
            @RequestBody @Valid AreaMaster areaMaster) {
        CityMaster city = cityService.getCityService(areaMaster.getCity().getCityId());
        if (city != null) {
            areaMaster.setCity(city);
            areaMaster.setAreaId(areaId);
            Boolean isUpdated = areaService.updateAreaService(areaMaster);
            if (isUpdated) {
                return new ResponseEntity<>("Area updated successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update Area.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid city ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteArea/{areaId}")
    public ResponseEntity<String> deleteArea(@PathVariable("areaId") @Positive(message = "Area ID must be positive") Integer areaId) {
        AreaMaster areaMaster = areaService.getAreaService(areaId);
        if (areaMaster != null) {
            Boolean isDeleted = areaService.deleteAreaService(areaMaster);
            if (isDeleted) {
                return new ResponseEntity<>("Area deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete Area.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getArea/{areaId}")
    public ResponseEntity<AreaMaster> getArea(@PathVariable("areaId") @Positive(message = "Area ID must be positive") Integer areaId) {
        AreaMaster areaMaster = areaService.getAreaService(areaId);
        if (areaMaster != null) {
            return new ResponseEntity<>(areaMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllAreas")
    public ResponseEntity<List<AreaMaster>> getAllAreas() {
        List<AreaMaster> allAreas = areaService.getAllAreaService();
        return new ResponseEntity<>(allAreas, HttpStatus.OK);
    }
}
