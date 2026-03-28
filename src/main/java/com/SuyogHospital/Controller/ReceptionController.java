package com.SuyogHospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SuyogHospital.Master.ReceptionMaster;
import com.SuyogHospital.ResponseDTO.ReceptionDTO;
import com.SuyogHospital.Service.ReceptionService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Reception")
public class ReceptionController {

    @Autowired
    private ReceptionService receptionService;

    @PostMapping("/add")
    public ResponseEntity<String> addReception(@RequestBody @Valid ReceptionDTO dto) {
        boolean isAdded = receptionService.addReceptionService(dto);
        return isAdded
                ? ResponseEntity.status(201).body("Reception added successfully!")
                : ResponseEntity.badRequest().body("Failed to add reception.");
    }
    

    @PutMapping("/update/{receptionId}")
    public ResponseEntity<String> updateReception(
            @PathVariable("receptionId") @Positive(message = "Reception ID must be positive") Integer receptionId,
            @RequestBody @Valid ReceptionDTO dto) {

        dto.setReceptionId(receptionId);  

        boolean isUpdated = receptionService.updateReceptionService(dto);
        return isUpdated
                ? ResponseEntity.ok("Reception updated successfully!")
                : ResponseEntity.badRequest().body("Failed to update reception. Past-date entries cannot be modified.");
    }

    @DeleteMapping("/delete/{receptionId}")
    public ResponseEntity<String> deleteReception(
            @PathVariable("receptionId") @Positive(message = "Reception ID must be positive") Integer receptionId) {

        ReceptionMaster reception = receptionService.getReceptionService(receptionId);
        if (reception == null) {
            return ResponseEntity.status(404).body("Reception not found.");
        }

        boolean isDeleted = receptionService.deleteReceptionService(reception);
        return isDeleted
                ? ResponseEntity.ok("Reception deleted successfully!")
                : ResponseEntity.badRequest().body("Failed to delete reception.");
    }

    @GetMapping("/get/{receptionId}")
    public ResponseEntity<ReceptionMaster> getReception(
            @PathVariable("receptionId") @Positive(message = "Reception ID must be positive") Integer receptionId) {

        ReceptionMaster reception = receptionService.getReceptionService(receptionId);
        return (reception != null)
                ? ResponseEntity.ok(reception)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ReceptionMaster>> getAllReceptions() {
        List<ReceptionMaster> all = receptionService.getAllReceptionService();
        return ResponseEntity.ok(all);
    }
    
    
    @GetMapping("/getAllReceptions")
    public List<ReceptionDTO> getAllReceptionsByMonthAndYear(@RequestParam("month") int month, @RequestParam("year") int year) {
        return receptionService.getAllReceptionsByMonthAndYear(month, year);
    }

}
