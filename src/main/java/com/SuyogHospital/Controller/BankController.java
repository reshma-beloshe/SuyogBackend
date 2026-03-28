package com.SuyogHospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SuyogHospital.Master.BankMaster;
import com.SuyogHospital.Service.BankService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/addBank")
    public ResponseEntity<String> addBank(@RequestBody @Valid BankMaster bankMaster) {
        Boolean isAdded = bankService.addBankService(bankMaster);
        return isAdded ? new ResponseEntity<>("Bank added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Bank.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateBank/{bankId}")
    public ResponseEntity<String> updateBank(
            @PathVariable("bankId") @Positive(message = "Bank ID must be positive") Integer bankId,
            @RequestBody @Valid BankMaster bankMaster) {
        bankMaster.setBankId(bankId);
        Boolean isUpdated = bankService.updateBankService(bankMaster);
        return isUpdated ? new ResponseEntity<>("Bank updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Bank.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteBank/{bankId}")
    public ResponseEntity<String> deleteBank(@PathVariable("bankId") @Positive(message = "Bank ID must be positive") Integer bankId) {
        BankMaster bankMaster = bankService.getBankService(bankId);
        if (bankMaster != null) {
            Boolean isDeleted = bankService.deleteBankService(bankMaster);
            return isDeleted ? new ResponseEntity<>("Bank deleted successfully!", HttpStatus.OK) :
                    new ResponseEntity<>("Failed to delete Bank.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Bank not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getBank/{bankId}")
    public ResponseEntity<BankMaster> getBank(@PathVariable("bankId") @Positive Integer bankId) {
        BankMaster bankMaster = bankService.getBankService(bankId);
        return bankMaster != null ? new ResponseEntity<>(bankMaster, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllBanks")
    public ResponseEntity<List<BankMaster>> getAllBanks() {
        List<BankMaster> allBanks = bankService.getAllBankService();
        return new ResponseEntity<>(allBanks, HttpStatus.OK);
    }
}
