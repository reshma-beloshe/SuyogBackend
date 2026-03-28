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

import com.SuyogHospital.Master.IncomeCategoryMaster;
import com.SuyogHospital.Service.IncomeCategoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/IncomeCategory")
public class IncomeCategoryController {

    @Autowired
    private IncomeCategoryService incomeCategoryService;

    @PostMapping("/addIncomeCategory")
    public ResponseEntity<String> addIncomeCategory(@RequestBody @Valid IncomeCategoryMaster incomeCategoryMaster) {
        Boolean isAdded = incomeCategoryService.addIncomeCategory(incomeCategoryMaster);
        return isAdded ?
                new ResponseEntity<>("Income Category added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Income Category.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateIncomeCategory/{incomeCategoryId}")
    public ResponseEntity<String> updateIncomeCategory(
            @PathVariable("incomeCategoryId") @Positive Integer incomeCategoryId,
            @RequestBody @Valid IncomeCategoryMaster incomeCategoryMaster) {
        incomeCategoryMaster.setInComeId(incomeCategoryId);
        Boolean isUpdated = incomeCategoryService.updateIncomeCategory(incomeCategoryMaster);
        return isUpdated ?
                new ResponseEntity<>("Income Category updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Income Category.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteIncomeCategory/{incomeCategoryId}")
    public ResponseEntity<String> deleteIncomeCategory(@PathVariable("incomeCategoryId") @Positive Integer incomeCategoryId) {
        IncomeCategoryMaster incomeCategoryMaster = incomeCategoryService.getIncomeCategory(incomeCategoryId);
        return incomeCategoryMaster != null && incomeCategoryService.deleteIncomeCategory(incomeCategoryMaster) ?
                new ResponseEntity<>("Income Category deleted successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to delete Income Category.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getIncomeCategory/{incomeCategoryId}")
    public ResponseEntity<IncomeCategoryMaster> getIncomeCategory(@PathVariable("incomeCategoryId") @Positive Integer incomeCategoryId) {
        return new ResponseEntity<>(incomeCategoryService.getIncomeCategory(incomeCategoryId), HttpStatus.OK);
    }

    @GetMapping("/getAllIncomeCategories")
    public ResponseEntity<List<IncomeCategoryMaster>> getAllIncomeCategories() {
        return new ResponseEntity<>(incomeCategoryService.getAllIncomeCategories(), HttpStatus.OK);
    }
}
