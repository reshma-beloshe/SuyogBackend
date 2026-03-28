package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.BlockHistoryMaster;
import com.SuyogHospital.Master.BlockMaster;
import com.SuyogHospital.Master.UnitMaster;
import com.SuyogHospital.Repository.BlockDAO;
import com.SuyogHospital.Repository.BlockHistoryDAO;
import com.SuyogHospital.Service.BlockService;
import com.SuyogHospital.Service.UnitService;

@Service
public class BlockServiceImpl implements BlockService {

    private static final Logger logger = LogManager.getLogger(BlockServiceImpl.class);

    @Autowired
    private BlockDAO blockDAO;

    @Autowired
    private BlockHistoryDAO blockHistoryDAO;

    @Autowired
    private UnitService unitService;

    @Override
    public Boolean addBlockService(BlockMaster blockMaster) {
        try {
            logger.info("In addBlockService");

            LocalDateTime now = LocalDateTime.now();
            blockMaster.setDate(now.toLocalDate());
            blockMaster.setTime(now.toLocalTime());

            // Validate and set Unit
            UnitMaster unit = unitService.getUnitService(blockMaster.getUnit().getUnitId());
            if (unit != null) {
                blockMaster.setUnit(unit);
            } else {
                logger.error("Invalid Unit ID");
                return false;
            }

            blockDAO.save(blockMaster);

            BlockHistoryMaster history = new BlockHistoryMaster();
            history.setBlockId(blockMaster.getBlockId());
            history.setBlockDescription(blockMaster.getBlockDescription());
            history.setUnitId(blockMaster.getUnit().getUnitId());
            history.setBlockStatus(blockMaster.getBlockStatus());
            history.setIsApproved(blockMaster.getIsApproved());
            history.setApprovedBy(blockMaster.getApprovedBy());
            history.setAddedBy(blockMaster.getAddedBy());
            history.setUpdatedBy(blockMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            blockHistoryDAO.save(history);
            logger.info("Out of addBlockService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateBlockService(BlockMaster blockMaster) {
        try {
            logger.info("In updateBlockService");

            BlockMaster existing = blockDAO.findById(blockMaster.getBlockId())
                    .orElseThrow(() -> new RuntimeException("Block not found"));

            LocalDateTime now = LocalDateTime.now();

            BlockHistoryMaster history = new BlockHistoryMaster();
            history.setBlockId(existing.getBlockId());
            history.setBlockDescription(existing.getBlockDescription());
            history.setUnitId(existing.getUnit().getUnitId());
            history.setBlockStatus(existing.getBlockStatus());
            history.setIsApproved(blockMaster.getIsApproved());
            history.setApprovedBy(blockMaster.getApprovedBy());
            history.setAddedBy(blockMaster.getAddedBy());
            history.setUpdatedBy(blockMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setBlockDescription(blockMaster.getBlockDescription());
            existing.setUnit(blockMaster.getUnit());
            existing.setBlockStatus(blockMaster.getBlockStatus());
            existing.setIsApproved(blockMaster.getIsApproved());
            existing.setApprovedBy(blockMaster.getApprovedBy());
            existing.setAddedBy(blockMaster.getAddedBy());
            existing.setUpdatedBy(blockMaster.getUpdatedBy());

            blockDAO.save(existing);
            blockHistoryDAO.save(history);
            logger.info("Out of updateBlockService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteBlockService(BlockMaster blockMaster) {
        try {
            logger.info("In deleteBlockService");

            BlockMaster existing = blockDAO.findById(blockMaster.getBlockId())
                    .orElseThrow(() -> new RuntimeException("Block not found"));

            BlockHistoryMaster history = new BlockHistoryMaster();
            history.setBlockId(existing.getBlockId());
            history.setBlockDescription(existing.getBlockDescription());
            history.setUnitId(existing.getUnit().getUnitId());
            history.setBlockStatus(existing.getBlockStatus());
            history.setIsApproved(blockMaster.getIsApproved());
            history.setApprovedBy(blockMaster.getApprovedBy());
            history.setAddedBy(blockMaster.getAddedBy());
            history.setUpdatedBy(blockMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            blockDAO.delete(existing);
            blockHistoryDAO.save(history);
            logger.info("Out of deleteBlockService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public BlockMaster getBlockService(Integer blockId) {
        try {
            logger.info("In getBlockService");
            BlockMaster blockMaster = blockDAO.findById(blockId).orElse(null);
            logger.info("Out of getBlockService");
            return blockMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<BlockMaster> getAllBlockService() {
        List<BlockMaster> allBlocks = new ArrayList<>();
        try {
            logger.info("In getAllBlockService");
            allBlocks = (List<BlockMaster>) blockDAO.findAll();
            logger.info("Out of getAllBlockService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allBlocks;
    }
}
