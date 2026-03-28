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

import com.SuyogHospital.Master.TarrifMaster;
import com.SuyogHospital.Service.TarrifService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Tarrif")
public class TarrifController {

    @Autowired
    private TarrifService tarrifService;

    @PostMapping("/addTarrif")
    public ResponseEntity<String> addTarrif(@RequestBody @Valid TarrifMaster tarrifMaster) {
        Boolean isAdded = tarrifService.addTarrifService(tarrifMaster);
        return isAdded ? new ResponseEntity<>("Tarrif added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Tarrif.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateTarrif/{tarrifId}")
    public ResponseEntity<String> updateTarrif(
            @PathVariable("tarrifId") @Positive(message = "Tarrif ID must be positive") Integer tarrifId,
            @RequestBody @Valid TarrifMaster tarrifMaster) {
        tarrifMaster.setTarrifId(tarrifId);
        Boolean isUpdated = tarrifService.updateTarrifService(tarrifMaster);
        return isUpdated ? new ResponseEntity<>("Tarrif updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Tarrif.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteTarrif/{tarrifId}")
    public ResponseEntity<String> deleteTarrif(@PathVariable("tarrifId") @Positive Integer tarrifId) {
        TarrifMaster tarrifMaster = tarrifService.getTarrifService(tarrifId);
        if (tarrifMaster != null) {
            Boolean isDeleted = tarrifService.deleteTarrifService(tarrifMaster);
            return isDeleted ? new ResponseEntity<>("Tarrif deleted successfully!", HttpStatus.OK) :
                    new ResponseEntity<>("Failed to delete Tarrif.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Tarrif not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getTarrif/{tarrifId}")
    public ResponseEntity<TarrifMaster> getTarrif(@PathVariable("tarrifId") @Positive Integer tarrifId) {
        TarrifMaster tarrifMaster = tarrifService.getTarrifService(tarrifId);
        return tarrifMaster != null ? new ResponseEntity<>(tarrifMaster, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllTarrifs")
    public ResponseEntity<List<TarrifMaster>> getAllTarrifs() {
        List<TarrifMaster> allTarrifs = tarrifService.getAllTarrifService();
        return new ResponseEntity<>(allTarrifs, HttpStatus.OK);
    }
}
