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

import com.SuyogHospital.Master.CityMaster;
import com.SuyogHospital.Master.TalukaMaster;
import com.SuyogHospital.Service.CityService;
import com.SuyogHospital.Service.TalukaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/City")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private TalukaService talukaService;

    @PostMapping("/addCity")
    public ResponseEntity<String> addCity(@RequestBody @Valid CityMaster cityMaster) {
    	TalukaMaster taluka = talukaService.getTalukaService(cityMaster.getTaluka().getTalId());
        if (taluka != null) {
            cityMaster.setTaluka(taluka);
            Boolean isAdded = cityService.addCityService(cityMaster);
            if (isAdded) {
                return new ResponseEntity<>("City added successfully!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to add City.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid state ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCity/{cityId}")
    public ResponseEntity<String> updateCity(
            @PathVariable("cityId") @Positive(message = "City ID must be positive") Integer cityId,
            @RequestBody @Valid CityMaster cityMaster) {
    	TalukaMaster taluka = talukaService.getTalukaService(cityMaster.getTaluka().getTalId());
        if (taluka != null) {
            cityMaster.setTaluka(taluka);
            cityMaster.setCityId(cityId);
            Boolean isUpdated = cityService.updateCityService(cityMaster);
            if (isUpdated) {
                return new ResponseEntity<>("City updated successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update City.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid state ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteCity/{cityId}")
    public ResponseEntity<String> deleteCity(@PathVariable("cityId") @Positive(message = "City ID must be positive") Integer cityId) {
        CityMaster cityMaster = cityService.getCityService(cityId);
        if (cityMaster != null) {
            Boolean isDeleted = cityService.deleteCityService(cityMaster);
            if (isDeleted) {
                return new ResponseEntity<>("City deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete City.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCity/{cityId}")
    public ResponseEntity<CityMaster> getCity(@PathVariable("cityId") @Positive(message = "City ID must be positive") Integer cityId) {
        CityMaster cityMaster = cityService.getCityService(cityId);
        if (cityMaster != null) {
            return new ResponseEntity<>(cityMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCities")
    public ResponseEntity<List<CityMaster>> getAllCities() {
        List<CityMaster> allCities = cityService.getAllCityService();
        return new ResponseEntity<>(allCities, HttpStatus.OK);
    }
}
