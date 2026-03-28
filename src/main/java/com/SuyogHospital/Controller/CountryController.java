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

import com.SuyogHospital.Master.CountryMaster;
import com.SuyogHospital.Service.CountryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/addCountry")
    public ResponseEntity<String> addCountry(@RequestBody @Valid CountryMaster countryMaster) {
        Boolean isAdded = countryService.addCountryService(countryMaster);
        if (isAdded) {
            return new ResponseEntity<>("Country added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add Country.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCountry/{countryId}")
    public ResponseEntity<String> updateCountry(
            @PathVariable("countryId") @Positive(message = "Country ID must be positive") Integer countryId,
            @RequestBody @Valid CountryMaster countryMaster) {
        countryMaster.setCountryId(countryId);
        Boolean isUpdated = countryService.updateCountryService(countryMaster);
        if (isUpdated) {
            return new ResponseEntity<>("Country updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Country.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteCountry/{countryId}")
    public ResponseEntity<String> deleteCountry(@PathVariable("countryId") @Positive(message = "Country ID must be positive") Integer countryId) {
        CountryMaster countryMaster = countryService.getCountryService(countryId);
        if (countryMaster != null) {
            Boolean isDeleted = countryService.deleteCountryService(countryMaster);
            if (isDeleted) {
                return new ResponseEntity<>("Country deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete Country.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCountry/{countryId}")
    public ResponseEntity<CountryMaster> getCountry(@PathVariable("countryId") @Positive(message = "Country ID must be positive") Integer countryId) {
        CountryMaster countryMaster = countryService.getCountryService(countryId);
        if (countryMaster != null) {
            return new ResponseEntity<>(countryMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCountries")
    public ResponseEntity<List<CountryMaster>> getAllCountries() {
        List<CountryMaster> allCountries = countryService.getAllCountryService();
        return new ResponseEntity<>(allCountries, HttpStatus.OK);
    }
}
