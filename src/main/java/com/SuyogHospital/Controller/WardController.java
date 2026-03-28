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

import com.SuyogHospital.Master.BlockMaster;
import com.SuyogHospital.Master.FloorMaster;
import com.SuyogHospital.Master.WardMaster;
import com.SuyogHospital.Service.BlockService;
import com.SuyogHospital.Service.FloorService;
import com.SuyogHospital.Service.WardService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Ward")
public class WardController {

    @Autowired
    private WardService wardService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private BlockService blockService;

    @PostMapping("/addWard")
    public ResponseEntity<String> addWard(@RequestBody @Valid WardMaster wardMaster) {
        FloorMaster floor = floorService.getFloorService(wardMaster.getFloor().getFloorId());
        BlockMaster block = blockService.getBlockService(wardMaster.getBlock().getBlockId());

        if (floor != null && block != null) {
            wardMaster.setFloor(floor);
            wardMaster.setBlock(block);
            Boolean isAdded = wardService.addWardService(wardMaster);
            return isAdded ? new ResponseEntity<>("Ward added successfully!", HttpStatus.CREATED)
                    : new ResponseEntity<>("Failed to add Ward.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Invalid Floor ID or Block ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateWard/{wardId}")
    public ResponseEntity<String> updateWard(
            @PathVariable("wardId") @Positive(message = "Ward ID must be positive") Integer wardId,
            @RequestBody @Valid WardMaster wardMaster) {
        FloorMaster floor = floorService.getFloorService(wardMaster.getFloor().getFloorId());
        BlockMaster block = blockService.getBlockService(wardMaster.getBlock().getBlockId());

        if (floor != null && block != null) {
            wardMaster.setFloor(floor);
            wardMaster.setBlock(block);
            wardMaster.setWardId(wardId);
            Boolean isUpdated = wardService.updateWardService(wardMaster);
            return isUpdated ? new ResponseEntity<>("Ward updated successfully!", HttpStatus.OK)
                    : new ResponseEntity<>("Failed to update Ward.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Invalid Floor ID or Block ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteWard/{wardId}")
    public ResponseEntity<String> deleteWard(@PathVariable("wardId") @Positive Integer wardId) {
        WardMaster wardMaster = wardService.getWardService(wardId);
        if (wardMaster != null) {
            Boolean isDeleted = wardService.deleteWardService(wardMaster);
            return isDeleted ? new ResponseEntity<>("Ward deleted successfully!", HttpStatus.OK)
                    : new ResponseEntity<>("Failed to delete Ward.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Ward not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getWard/{wardId}")
    public ResponseEntity<WardMaster> getWard(@PathVariable("wardId") @Positive Integer wardId) {
        WardMaster wardMaster = wardService.getWardService(wardId);
        return wardMaster != null ? new ResponseEntity<>(wardMaster, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllWards")
    public ResponseEntity<List<WardMaster>> getAllWards() {
        List<WardMaster> allWards = wardService.getAllWardService();
        return new ResponseEntity<>(allWards, HttpStatus.OK);
    }
}
