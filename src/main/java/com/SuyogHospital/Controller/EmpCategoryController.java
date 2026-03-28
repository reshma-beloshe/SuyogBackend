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

import com.SuyogHospital.Master.EmpCategoryMaster;
import com.SuyogHospital.Service.EmpCategoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/EmpCategory")
public class EmpCategoryController {

    @Autowired
    private EmpCategoryService empCategoryService;

    @PostMapping("/addEmpCategory")
    public ResponseEntity<String> addEmpCategory(@RequestBody @Valid EmpCategoryMaster empCategory) {
        Boolean isAdded = empCategoryService.addEmpCategory(empCategory);
        if (isAdded) {
            return new ResponseEntity<>("Employee Category added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add Employee Category.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateEmpCategory/{empCategoryId}")
    public ResponseEntity<String> updateEmpCategory(
            @PathVariable("empCategoryId") @Positive(message = "Employee Category ID must be positive") Integer empCategoryId,
            @RequestBody @Valid EmpCategoryMaster empCategory) {
        empCategory.setEmpCategoryId(empCategoryId);
        Boolean isUpdated = empCategoryService.updateEmpCategory(empCategory);
        if (isUpdated) {
            return new ResponseEntity<>("Employee Category updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Employee Category.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteEmpCategory/{empCategoryId}")
    public ResponseEntity<String> deleteEmpCategory(@PathVariable("empCategoryId") @Positive(message = "Employee Category ID must be positive") Integer empCategoryId) {
    	EmpCategoryMaster empCategory = empCategoryService.getEmpCategory(empCategoryId);
        if (empCategory != null) {
            Boolean isDeleted = empCategoryService.deleteEmpCategory(empCategory);
            if (isDeleted) {
                return new ResponseEntity<>("Employee Category deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Failed to delete Employee Category.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getEmpCategory/{empCategoryId}")
    public ResponseEntity<EmpCategoryMaster> getEmpCategory(@PathVariable("empCategoryId") @Positive(message = "Employee Category ID must be positive") Integer empCategoryId) {
    	EmpCategoryMaster empCategory = empCategoryService.getEmpCategory(empCategoryId);
        if (empCategory != null) {
            return new ResponseEntity<>(empCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllEmpCategories")
    public ResponseEntity<List<EmpCategoryMaster>> getAllEmpCategories() {
        List<EmpCategoryMaster> allCategories = empCategoryService.getAllEmpCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
}
