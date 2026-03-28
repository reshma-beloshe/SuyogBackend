package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.BlockMaster;
import com.SuyogHospital.Master.FloorMaster;
import com.SuyogHospital.Master.WardHistoryMaster;
import com.SuyogHospital.Master.WardMaster;
import com.SuyogHospital.Repository.WardDAO;
import com.SuyogHospital.Repository.WardHistoryDAO;
import com.SuyogHospital.Service.BlockService;
import com.SuyogHospital.Service.FloorService;
import com.SuyogHospital.Service.WardService;

@Service
public class WardServiceImpl implements WardService {

    private static final Logger logger = LogManager.getLogger(WardServiceImpl.class);

    @Autowired
    private WardDAO wardDAO;

    @Autowired
    private WardHistoryDAO wardHistoryDAO;

    @Autowired
    private FloorService floorService;

    @Autowired
    private BlockService blockService;

    @Override
    public Boolean addWardService(WardMaster wardMaster) {
        try {
            logger.info("In addWardService");

            LocalDateTime now = LocalDateTime.now();
            wardMaster.setDate(now.toLocalDate());
            wardMaster.setTime(now.toLocalTime());

            // Validate and set Floor
            FloorMaster floor = floorService.getFloorService(wardMaster.getFloor().getFloorId());
            if (floor != null) {
                wardMaster.setFloor(floor);
            } else {
                logger.error("Invalid Floor ID");
                return false;
            }

            // Validate and set Block
            BlockMaster block = blockService.getBlockService(wardMaster.getBlock().getBlockId());
            if (block != null) {
                wardMaster.setBlock(block);
            } else {
                logger.error("Invalid Block ID");
                return false;
            }

            wardDAO.save(wardMaster);

            WardHistoryMaster history = new WardHistoryMaster();
            history.setWardId(wardMaster.getWardId());
            history.setWardDescription(wardMaster.getWardDescription());
            history.setWardStatus(wardMaster.getWardStatus());
            history.setSex(wardMaster.getSex());
            history.setIsApproved(wardMaster.getIsApproved());
            history.setApprovedBy(wardMaster.getApprovedBy());
            history.setAddedBy(wardMaster.getAddedBy());
            history.setUpdatedBy(wardMaster.getUpdatedBy());
            history.setFloorId(wardMaster.getFloor().getFloorId());
            history.setBlockId(wardMaster.getBlock().getBlockId());
            history.setAction("Create");
            history.setTimestamp(now);

            wardHistoryDAO.save(history);
            logger.info("Out of addWardService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateWardService(WardMaster wardMaster) {
        try {
            logger.info("In updateWardService");

            WardMaster existing = wardDAO.findById(wardMaster.getWardId())
                    .orElseThrow(() -> new RuntimeException("Ward not found"));

            LocalDateTime now = LocalDateTime.now();

            WardHistoryMaster history = new WardHistoryMaster();
            history.setWardId(existing.getWardId());
            history.setWardDescription(existing.getWardDescription());
            history.setWardStatus(existing.getWardStatus());
            history.setSex(existing.getSex());
            history.setIsApproved(wardMaster.getIsApproved());
            history.setApprovedBy(wardMaster.getApprovedBy());
            history.setAddedBy(wardMaster.getAddedBy());
            history.setUpdatedBy(wardMaster.getUpdatedBy());
            history.setFloorId(existing.getFloor().getFloorId());
            history.setBlockId(existing.getBlock().getBlockId());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setWardDescription(wardMaster.getWardDescription());
            existing.setWardStatus(wardMaster.getWardStatus());
            existing.setSex(wardMaster.getSex());
            existing.setIsApproved(wardMaster.getIsApproved());
            existing.setApprovedBy(wardMaster.getApprovedBy());
            existing.setAddedBy(wardMaster.getAddedBy());
            existing.setUpdatedBy(wardMaster.getUpdatedBy());

            wardDAO.save(existing);
            wardHistoryDAO.save(history);
            logger.info("Out of updateWardService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteWardService(WardMaster wardMaster) {
        try {
            logger.info("In deleteWardService");

            WardMaster existing = wardDAO.findById(wardMaster.getWardId())
                    .orElseThrow(() -> new RuntimeException("Ward not found"));

            WardHistoryMaster history = new WardHistoryMaster();
            history.setWardId(existing.getWardId());
            history.setWardDescription(existing.getWardDescription());
            history.setWardStatus(existing.getWardStatus());
            history.setSex(existing.getSex());
            history.setIsApproved(wardMaster.getIsApproved());
            history.setApprovedBy(wardMaster.getApprovedBy());
            history.setAddedBy(wardMaster.getAddedBy());
            history.setUpdatedBy(wardMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            wardDAO.delete(existing);
            wardHistoryDAO.save(history);
            logger.info("Out of deleteWardService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public WardMaster getWardService(Integer wardId) {
        try {
            logger.info("In getWardService");
            WardMaster wardMaster = wardDAO.findById(wardId).orElse(null);
            logger.info("Out of getWardService");
            return wardMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<WardMaster> getAllWardService() {
        List<WardMaster> allWards = new ArrayList<>();
        try {
            logger.info("In getAllWardService");
            allWards = (List<WardMaster>) wardDAO.findAll();
            logger.info("Out of getAllWardService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allWards;
    }
}
