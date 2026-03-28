package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.CityHistoryMaster;
import com.SuyogHospital.Master.CityMaster;
import com.SuyogHospital.Master.TalukaMaster;
import com.SuyogHospital.Repository.CityDAO;
import com.SuyogHospital.Repository.CityHistoryDAO;
import com.SuyogHospital.Service.CityService;
import com.SuyogHospital.Service.TalukaService;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger logger = LogManager.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private CityHistoryDAO cityHistoryDAO;
    
    @Autowired
    private TalukaService talukaService;

    @Override
    public Boolean addCityService(CityMaster cityMaster) {
        try {
            logger.info("In addCityService");
            LocalDateTime now = LocalDateTime.now();
            cityMaster.setDate(now.toLocalDate());
            cityMaster.setTime(now.toLocalTime());

            // Validate and set State
            TalukaMaster taluka = talukaService.getTalukaService(cityMaster.getTaluka().getTalId());
            if (taluka != null) {
                cityMaster.setTaluka(taluka);
            } else {
                logger.error("Invalid Taluka ID");
                return false;
            }

            cityDAO.save(cityMaster);

            CityHistoryMaster history = new CityHistoryMaster();
            history.setCityId(cityMaster.getCityId());
            history.setCityName(cityMaster.getCityName());
            history.setCityDesc(cityMaster.getCityDesc());
            history.setTalId(cityMaster.getTaluka().getTalId());
            history.setIsApproved(cityMaster.getIsApproved());
            history.setApprovedBy(cityMaster.getApprovedBy());
            history.setAddedBy(cityMaster.getAddedBy());
            history.setUpdatedBy(cityMaster.getUpdatedBy());
            history.setAction("Create");
            history.setTimestamp(now);

            cityHistoryDAO.save(history);
            logger.info("Out of addCityService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateCityService(CityMaster cityMaster) {
        try {
            logger.info("In updateCityService");

            CityMaster existing = cityDAO.findById(cityMaster.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found"));

            LocalDateTime now = LocalDateTime.now();

            CityHistoryMaster history = new CityHistoryMaster();
            history.setCityId(existing.getCityId());
            history.setCityName(existing.getCityName());
            history.setCityDesc(existing.getCityDesc());
            history.setTalId(cityMaster.getTaluka().getTalId());
            history.setIsApproved(cityMaster.getIsApproved());
            history.setApprovedBy(cityMaster.getApprovedBy());
            history.setAddedBy(cityMaster.getAddedBy());
            history.setUpdatedBy(cityMaster.getUpdatedBy());
            history.setAction("Update");
            history.setTimestamp(now);

            existing.setCityName(cityMaster.getCityName());
            existing.setCityDesc(cityMaster.getCityDesc());
            existing.setIsApproved(cityMaster.getIsApproved());
            existing.setApprovedBy(cityMaster.getApprovedBy());
            existing.setAddedBy(cityMaster.getAddedBy());
            existing.setUpdatedBy(cityMaster.getUpdatedBy());

            cityDAO.save(existing);
            cityHistoryDAO.save(history);
            logger.info("Out of updateCityService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteCityService(CityMaster cityMaster) {
        try {
            logger.info("In deleteCityService");

            CityMaster existing = cityDAO.findById(cityMaster.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found"));

            CityHistoryMaster history = new CityHistoryMaster();
            history.setCityId(existing.getCityId());
            history.setCityName(existing.getCityName());
            history.setCityDesc(existing.getCityDesc());
            history.setTalId(cityMaster.getTaluka().getTalId());
            history.setIsApproved(cityMaster.getIsApproved());
            history.setApprovedBy(cityMaster.getApprovedBy());
            history.setAddedBy(cityMaster.getAddedBy());
            history.setUpdatedBy(cityMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            cityDAO.delete(existing);
            cityHistoryDAO.save(history);
            logger.info("Out of deleteCityService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public CityMaster getCityService(Integer cityId) {
        try {
            logger.info("In getCityService");
            CityMaster cityMaster = cityDAO.findById(cityId).orElse(null);
            logger.info("Out of getCityService");
            return cityMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<CityMaster> getAllCityService() {
        List<CityMaster> allCities = new ArrayList<>();
        try {
            logger.info("In getAllCityService");
            allCities = (List<CityMaster>) cityDAO.findAll();
            logger.info("Out of getAllCityService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allCities;
    }
}
