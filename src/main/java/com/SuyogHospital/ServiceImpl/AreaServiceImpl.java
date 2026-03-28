package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.AreaHistoryMaster;
import com.SuyogHospital.Master.AreaMaster;
import com.SuyogHospital.Master.CityMaster;
import com.SuyogHospital.Repository.AreaDAO;
import com.SuyogHospital.Repository.AreaHistoryDAO;
import com.SuyogHospital.Service.AreaService;
import com.SuyogHospital.Service.CityService;

@Service
public class AreaServiceImpl implements AreaService {

    private static final Logger logger = LogManager.getLogger(AreaServiceImpl.class);

    @Autowired
    private AreaDAO areaDAO;

    @Autowired
    private AreaHistoryDAO areaHistoryDAO;
    
    @Autowired
    private CityService cityService;

    @Override
    public Boolean addAreaService(AreaMaster areaMaster) {
        try {
            logger.info("In addAreaService");
            LocalDateTime now = LocalDateTime.now();
            areaMaster.setDate(now.toLocalDate());
            areaMaster.setTime(now.toLocalTime());

            // Validate and set City
            CityMaster city = cityService.getCityService(areaMaster.getCity().getCityId());
            if (city != null) {
                areaMaster.setCity(city);
            } else {
                logger.error("Invalid City ID");
                return false;
            }

            areaDAO.save(areaMaster);

            AreaHistoryMaster history = new AreaHistoryMaster();
            history.setAreaId(areaMaster.getAreaId());
            history.setAreaName(areaMaster.getAreaName());
            history.setAreaDesc(areaMaster.getAreaDesc());
            history.setCityId(areaMaster.getCity().getCityId());
            history.setIsApproved(areaMaster.getIsApproved());
            history.setApprovedBy(areaMaster.getApprovedBy());
            history.setAddedBy(areaMaster.getAddedBy());
            history.setUpdatedBy(areaMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            areaHistoryDAO.save(history);
            logger.info("Out of addAreaService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateAreaService(AreaMaster areaMaster) {
        try {
            logger.info("In updateAreaService");

            AreaMaster existing = areaDAO.findById(areaMaster.getAreaId())
                    .orElseThrow(() -> new RuntimeException("Area not found"));

            LocalDateTime now = LocalDateTime.now();

            AreaHistoryMaster history = new AreaHistoryMaster();
            history.setAreaId(existing.getAreaId());
            history.setAreaName(existing.getAreaName());
            history.setAreaDesc(existing.getAreaDesc());
            history.setCityId(areaMaster.getCity().getCityId());
            history.setIsApproved(areaMaster.getIsApproved());
            history.setApprovedBy(areaMaster.getApprovedBy());
            history.setAddedBy(areaMaster.getAddedBy());
            history.setUpdatedBy(areaMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setAreaName(areaMaster.getAreaName());
            existing.setAreaDesc(areaMaster.getAreaDesc());
            existing.setIsApproved(areaMaster.getIsApproved());
            existing.setApprovedBy(areaMaster.getApprovedBy());
            existing.setAddedBy(areaMaster.getAddedBy());
            existing.setUpdatedBy(areaMaster.getUpdatedBy());

            areaDAO.save(existing);
            areaHistoryDAO.save(history);
            logger.info("Out of updateAreaService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteAreaService(AreaMaster areaMaster) {
        try {
            logger.info("In deleteAreaService");

            AreaMaster existing = areaDAO.findById(areaMaster.getAreaId())
                    .orElseThrow(() -> new RuntimeException("Area not found"));

            AreaHistoryMaster history = new AreaHistoryMaster();
            history.setAreaId(existing.getAreaId());
            history.setAreaName(existing.getAreaName());
            history.setAreaDesc(existing.getAreaDesc());
            history.setCityId(areaMaster.getCity().getCityId());
            history.setIsApproved(areaMaster.getIsApproved());
            history.setApprovedBy(areaMaster.getApprovedBy());
            history.setAddedBy(areaMaster.getAddedBy());
            history.setUpdatedBy(areaMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            areaDAO.delete(existing);
            areaHistoryDAO.save(history);
            logger.info("Out of deleteAreaService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public AreaMaster getAreaService(Integer areaId) {
        try {
            logger.info("In getAreaService");
            AreaMaster areaMaster = areaDAO.findById(areaId).orElse(null);
            logger.info("Out of getAreaService");
            return areaMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<AreaMaster> getAllAreaService() {
        List<AreaMaster> allAreas = new ArrayList<>();
        try {
            logger.info("In getAllAreaService");
            allAreas = (List<AreaMaster>) areaDAO.findAll();
            logger.info("Out of getAllAreaService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allAreas;
    }
}
