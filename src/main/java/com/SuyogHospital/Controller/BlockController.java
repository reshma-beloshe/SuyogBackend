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
import com.SuyogHospital.Master.UnitMaster;
import com.SuyogHospital.Service.BlockService;
import com.SuyogHospital.Service.UnitService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/Block")
public class BlockController {

    @Autowired
    private BlockService blockService;

    @Autowired
    private UnitService unitService;

    @PostMapping("/addBlock")
    public ResponseEntity<String> addBlock(@RequestBody @Valid BlockMaster blockMaster) {
        UnitMaster unit = unitService.getUnitService(blockMaster.getUnit().getUnitId());
        if (unit != null) {
            blockMaster.setUnit(unit);
            Boolean isAdded = blockService.addBlockService(blockMaster);
            return isAdded ? new ResponseEntity<>("Block added successfully!", HttpStatus.CREATED)
                    : new ResponseEntity<>("Failed to add Block.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Invalid Unit ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateBlock/{blockId}")
    public ResponseEntity<String> updateBlock(
            @PathVariable("blockId") @Positive(message = "Block ID must be positive") Integer blockId,
            @RequestBody @Valid BlockMaster blockMaster) {
        UnitMaster unit = unitService.getUnitService(blockMaster.getUnit().getUnitId());
        if (unit != null) {
            blockMaster.setUnit(unit);
            blockMaster.setBlockId(blockId);
            Boolean isUpdated = blockService.updateBlockService(blockMaster);
            return isUpdated ? new ResponseEntity<>("Block updated successfully!", HttpStatus.OK)
                    : new ResponseEntity<>("Failed to update Block.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Invalid Unit ID.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteBlock/{blockId}")
    public ResponseEntity<String> deleteBlock(@PathVariable("blockId") @Positive Integer blockId) {
        BlockMaster blockMaster = blockService.getBlockService(blockId);
        if (blockMaster != null) {
            Boolean isDeleted = blockService.deleteBlockService(blockMaster);
            return isDeleted ? new ResponseEntity<>("Block deleted successfully!", HttpStatus.OK)
                    : new ResponseEntity<>("Failed to delete Block.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Block not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getBlock/{blockId}")
    public ResponseEntity<BlockMaster> getBlock(@PathVariable("blockId") @Positive Integer blockId) {
        BlockMaster blockMaster = blockService.getBlockService(blockId);
        return blockMaster != null ? new ResponseEntity<>(blockMaster, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllBlocks")
    public ResponseEntity<List<BlockMaster>> getAllBlocks() {
        List<BlockMaster> allBlocks = blockService.getAllBlockService();
        return new ResponseEntity<>(allBlocks, HttpStatus.OK);
    }
}
