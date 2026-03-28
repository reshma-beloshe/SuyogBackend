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

import com.SuyogHospital.Master.PrefixMaster;
import com.SuyogHospital.Service.PrefixService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Prefix")
public class PrefixController {

    @Autowired
    private PrefixService prefixService;

    @PostMapping("/addPrefix")
    public ResponseEntity<String> addPrefix(@RequestBody @Valid PrefixMaster prefixMaster) {
        Boolean isAdded = prefixService.addPrefix(prefixMaster);
        if (isAdded) {
            return new ResponseEntity<>("Prefix added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add Prefix.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updatePrefix/{prefixId}")
    public ResponseEntity<String> updatePrefix(
            @PathVariable("prefixId") @Positive(message = "Prefix ID must be positive") Integer prefixId,
            @RequestBody @Valid PrefixMaster prefixMaster) {
        prefixMaster.setPrefixId(prefixId);
        Boolean isUpdated = prefixService.updatePrefix(prefixMaster);
        if (isUpdated) {
            return new ResponseEntity<>("Prefix updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Prefix.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletePrefix/{prefixId}")
    public ResponseEntity<String> deletePrefix(@PathVariable("prefixId") @Positive(message = "Prefix ID must be positive") Integer prefixId) {
        PrefixMaster prefixMaster = prefixService.getPrefix(prefixId);
        if (prefixMaster != null) {
            Boolean isDeleted = prefixService.deletePrefix(prefixMaster);
            if (isDeleted) {
                return new ResponseEntity<>("Prefix deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete Prefix.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getPrefix/{prefixId}")
    public ResponseEntity<PrefixMaster> getPrefix(@PathVariable("prefixId") @Positive(message = "Prefix ID must be positive") Integer prefixId) {
        PrefixMaster prefixMaster = prefixService.getPrefix(prefixId);
        if (prefixMaster != null) {
            return new ResponseEntity<>(prefixMaster, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllPrefixes")
    public ResponseEntity<List<PrefixMaster>> getAllPrefixes() {
        List<PrefixMaster> allPrefixes = prefixService.getAllPrefixes();
        return new ResponseEntity<>(allPrefixes, HttpStatus.OK);
    }
}
