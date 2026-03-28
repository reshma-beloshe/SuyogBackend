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

import com.SuyogHospital.Master.FloorMaster;
import com.SuyogHospital.Service.FloorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Floor")
public class FloorController {

    @Autowired
    private FloorService floorService;

    @PostMapping("/addFloor")
    public ResponseEntity<String> addFloor(@RequestBody @Valid FloorMaster floorMaster) {
        Boolean isAdded = floorService.addFloorService(floorMaster);
        return isAdded ? new ResponseEntity<>("Floor added successfully!", HttpStatus.CREATED) :
                new ResponseEntity<>("Failed to add Floor.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateFloor/{floorId}")
    public ResponseEntity<String> updateFloor(
            @PathVariable("floorId") @Positive Integer floorId,
            @RequestBody @Valid FloorMaster floorMaster) {
        floorMaster.setFloorId(floorId);
        Boolean isUpdated = floorService.updateFloorService(floorMaster);
        return isUpdated ? new ResponseEntity<>("Floor updated successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update Floor.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteFloor/{floorId}")
    public ResponseEntity<String> deleteFloor(@PathVariable("floorId") @Positive Integer floorId) {
        FloorMaster floorMaster = floorService.getFloorService(floorId);
        if (floorMaster != null) {
            Boolean isDeleted = floorService.deleteFloorService(floorMaster);
            return isDeleted ? new ResponseEntity<>("Floor deleted successfully!", HttpStatus.OK) :
                    new ResponseEntity<>("Failed to delete Floor.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Floor not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getFloor/{floorId}")
    public ResponseEntity<FloorMaster> getFloor(@PathVariable("floorId") @Positive Integer floorId) {
        FloorMaster floorMaster = floorService.getFloorService(floorId);
        return floorMaster != null ? new ResponseEntity<>(floorMaster, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllFloors")
    public ResponseEntity<List<FloorMaster>> getAllFloors() {
        List<FloorMaster> allFloors = floorService.getAllFloorService();
        return new ResponseEntity<>(allFloors, HttpStatus.OK);
    }
}
