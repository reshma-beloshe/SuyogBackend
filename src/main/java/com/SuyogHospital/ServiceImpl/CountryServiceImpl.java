package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.CountryHistoryMaster;
import com.SuyogHospital.Master.CountryMaster;
import com.SuyogHospital.Repository.CountryDAO;
import com.SuyogHospital.Repository.CountryHistoryDAO;
import com.SuyogHospital.Service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

    private static final Logger logger = LogManager.getLogger(CountryServiceImpl.class);

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private CountryHistoryDAO countryHistoryDAO;

    @Override
    public Boolean addCountryService(CountryMaster countryMaster) {
        try {
            logger.info("In addCountryService");

            // Set current date/time for created
            LocalDateTime now = LocalDateTime.now();
            countryMaster.setDate(now.toLocalDate());
            countryMaster.setTime(now.toLocalTime());

            countryDAO.save(countryMaster);

            CountryHistoryMaster history = new CountryHistoryMaster();
            history.setCountryId(countryMaster.getCountryId());
            history.setCountryName(countryMaster.getCountryName());
            history.setCountryDesc(countryMaster.getCountryDesc());
            history.setIsApproved(countryMaster.getIsApproved());
            history.setApprovedBy(countryMaster.getApprovedBy());
            history.setAddedBy(countryMaster.getAddedBy());
            history.setUpdatedBy(countryMaster.getUpdatedBy());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            countryHistoryDAO.save(history);
            logger.info("Out of addCountryService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }



    @Override
    public Boolean updateCountryService(CountryMaster countryMaster) {
        try {
            logger.info("In updateCountryService");

            CountryMaster existing = countryDAO.findById(countryMaster.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));

            LocalDateTime now = LocalDateTime.now();

            CountryHistoryMaster history = new CountryHistoryMaster();
            history.setCountryId(existing.getCountryId());
            history.setCountryName(existing.getCountryName());
            history.setCountryDesc(existing.getCountryDesc());
            history.setIsApproved(countryMaster.getIsApproved());
            history.setApprovedBy(countryMaster.getApprovedBy());
            history.setAddedBy(countryMaster.getAddedBy());
            history.setUpdatedBy(countryMaster.getUpdatedBy());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            // Update values
            existing.setCountryName(countryMaster.getCountryName());
            existing.setCountryDesc(countryMaster.getCountryDesc());
            existing.setIsApproved(countryMaster.getIsApproved());
            existing.setApprovedBy(countryMaster.getApprovedBy());
            existing.setAddedBy(countryMaster.getAddedBy());
            existing.setUpdatedBy(countryMaster.getUpdatedBy());

            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            countryDAO.save(existing);
            countryHistoryDAO.save(history);
            logger.info("Out of updateCountryService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }



    @Override
    public Boolean deleteCountryService(CountryMaster countryMaster) {
        try {
            logger.info("In deleteCountryService");
            CountryMaster existing = countryDAO.findById(countryMaster.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));

            CountryHistoryMaster history = new CountryHistoryMaster();
            history.setCountryId(existing.getCountryId());
            history.setCountryName(existing.getCountryName());
            history.setCountryDesc(existing.getCountryDesc());
            history.setIsApproved(countryMaster.getIsApproved());
            history.setApprovedBy(countryMaster.getApprovedBy());
            history.setAddedBy(countryMaster.getAddedBy());
            history.setUpdatedBy(countryMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            countryDAO.delete(existing);
            countryHistoryDAO.save(history);
            logger.info("Out of deleteCountryService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public CountryMaster getCountryService(Integer countryId) {
        try {
            logger.info("In getCountryService");
            CountryMaster countryMaster = countryDAO.findById(countryId).orElse(null);
            logger.info("Out of getCountryService");
            return countryMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<CountryMaster> getAllCountryService() {
        List<CountryMaster> allCountries = new ArrayList<>();
        try {
            logger.info("In getAllCountryService");
            allCountries = (List<CountryMaster>) countryDAO.findAll();
            logger.info("Out of getAllCountryService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allCountries;
    }
}
