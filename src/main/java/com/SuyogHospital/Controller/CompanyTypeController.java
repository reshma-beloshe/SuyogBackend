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

import com.SuyogHospital.Master.CompanyTypeMaster;
import com.SuyogHospital.Service.CompanyTypeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/CompanyType")
public class CompanyTypeController {

    @Autowired
    private CompanyTypeService companyTypeService;

    @PostMapping("/addCompanyType")
    public ResponseEntity<String> addCompanyType(@RequestBody @Valid CompanyTypeMaster companyTypeMaster) {
        Boolean isAdded = companyTypeService.addCompanyType(companyTypeMaster);
        return isAdded ?
                new ResponseEntity<>("Company Type added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Company Type.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateCompanyType/{companyTypeId}")
    public ResponseEntity<String> updateCompanyType(
            @PathVariable("companyTypeId") @Positive Integer companyTypeId,
            @RequestBody @Valid CompanyTypeMaster companyTypeMaster) {
        companyTypeMaster.setCompanyTypeId(companyTypeId);
        Boolean isUpdated = companyTypeService.updateCompanyType(companyTypeMaster);
        return isUpdated ?
                new ResponseEntity<>("Company Type updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Company Type.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteCompanyType/{companyTypeId}")
    public ResponseEntity<String> deleteCompanyType(@PathVariable("companyTypeId") @Positive Integer companyTypeId) {
        CompanyTypeMaster companyTypeMaster = companyTypeService.getCompanyType(companyTypeId);
        return companyTypeMaster != null && companyTypeService.deleteCompanyType(companyTypeMaster) ?
                new ResponseEntity<>("Company Type deleted successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to delete Company Type.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCompanyType/{companyTypeId}")
    public ResponseEntity<CompanyTypeMaster> getCompanyType(@PathVariable("companyTypeId") @Positive Integer companyTypeId) {
        return new ResponseEntity<>(companyTypeService.getCompanyType(companyTypeId), HttpStatus.OK);
    }

    @GetMapping("/getAllCompanyTypes")
    public ResponseEntity<List<CompanyTypeMaster>> getAllCompanyTypes() {
        return new ResponseEntity<>(companyTypeService.getAllCompanyTypes(), HttpStatus.OK);
    }
}
