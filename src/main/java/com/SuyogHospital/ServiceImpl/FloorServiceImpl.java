package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.FloorHistoryMaster;
import com.SuyogHospital.Master.FloorMaster;
import com.SuyogHospital.Repository.FloorDAO;
import com.SuyogHospital.Repository.FloorHistoryDAO;
import com.SuyogHospital.Service.FloorService;

@Service
public class FloorServiceImpl implements FloorService {

    private static final Logger logger = LogManager.getLogger(FloorServiceImpl.class);

    @Autowired
    private FloorDAO floorDAO;

    @Autowired
    private FloorHistoryDAO floorHistoryDAO;

    @Override
    public Boolean addFloorService(FloorMaster floorMaster) {
        try {
            logger.info("In addFloorService");

            LocalDateTime now = LocalDateTime.now();
            floorMaster.setDate(now.toLocalDate());
            floorMaster.setTime(now.toLocalTime());
            floorDAO.save(floorMaster);

            FloorHistoryMaster history = new FloorHistoryMaster();
            history.setFloorId(floorMaster.getFloorId());
            history.setFloorDescription(floorMaster.getFloorDescription());
            history.setFloorStatus(floorMaster.getFloorStatus());
            history.setIsApproved(floorMaster.getIsApproved());
            history.setApprovedBy(floorMaster.getApprovedBy());
            history.setAddedBy(floorMaster.getAddedBy());
            history.setUpdatedBy(floorMaster.getUpdatedBy());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            floorHistoryDAO.save(history);
            logger.info("Out of addFloorService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateFloorService(FloorMaster floorMaster) {
        try {
            logger.info("In updateFloorService");

            FloorMaster existing = floorDAO.findById(floorMaster.getFloorId())
                    .orElseThrow(() -> new RuntimeException("Floor not found"));

            LocalDateTime now = LocalDateTime.now();

            FloorHistoryMaster history = new FloorHistoryMaster();
            history.setFloorId(existing.getFloorId());
            history.setFloorDescription(existing.getFloorDescription());
            history.setFloorStatus(existing.getFloorStatus());
            history.setIsApproved(floorMaster.getIsApproved());
            history.setApprovedBy(floorMaster.getApprovedBy());
            history.setAddedBy(floorMaster.getAddedBy());
            history.setUpdatedBy(floorMaster.getUpdatedBy());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            existing.setFloorDescription(floorMaster.getFloorDescription());
            existing.setFloorStatus(floorMaster.getFloorStatus());
            existing.setIsApproved(floorMaster.getIsApproved());
            existing.setApprovedBy(floorMaster.getApprovedBy());
            existing.setAddedBy(floorMaster.getAddedBy());
            existing.setUpdatedBy(floorMaster.getUpdatedBy());

            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            floorDAO.save(existing);
            floorHistoryDAO.save(history);
            logger.info("Out of updateFloorService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteFloorService(FloorMaster floorMaster) {
        try {
            logger.info("In deleteFloorService");

            FloorMaster existing = floorDAO.findById(floorMaster.getFloorId())
                    .orElseThrow(() -> new RuntimeException("Floor not found"));

            FloorHistoryMaster history = new FloorHistoryMaster();
            history.setFloorId(existing.getFloorId());
            history.setFloorDescription(existing.getFloorDescription());
            history.setFloorStatus(existing.getFloorStatus());
            history.setIsApproved(floorMaster.getIsApproved());
            history.setApprovedBy(floorMaster.getApprovedBy());
            history.setAddedBy(floorMaster.getAddedBy());
            history.setUpdatedBy(floorMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            floorDAO.delete(existing);
            floorHistoryDAO.save(history);
            logger.info("Out of deleteFloorService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public FloorMaster getFloorService(Integer floorId) {
        try {
            logger.info("In getFloorService");
            FloorMaster floorMaster = floorDAO.findById(floorId).orElse(null);
            logger.info("Out of getFloorService");
            return floorMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<FloorMaster> getAllFloorService() {
        List<FloorMaster> allFloors = new ArrayList<>();
        try {
            logger.info("In getAllFloorService");
            allFloors = (List<FloorMaster>) floorDAO.findAll();
            logger.info("Out of getAllFloorService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allFloors;
    }
}
