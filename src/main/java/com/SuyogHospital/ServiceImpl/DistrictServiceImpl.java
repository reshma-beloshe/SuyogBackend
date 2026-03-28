package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.DistrictHistoryMaster;
import com.SuyogHospital.Master.DistrictMaster;
import com.SuyogHospital.Master.StateMaster;
import com.SuyogHospital.Repository.DistrictDAO;
import com.SuyogHospital.Repository.DistrictHistoryDAO;
import com.SuyogHospital.Service.DistrictService;
import com.SuyogHospital.Service.StateService;

@Service
public class DistrictServiceImpl implements DistrictService {

    private static final Logger logger = LogManager.getLogger(DistrictServiceImpl.class);

    @Autowired
    private DistrictDAO districtDAO;

    @Autowired
    private DistrictHistoryDAO districtHistoryDAO;
    
    @Autowired
    private StateService stateService;

    @Override
    public Boolean addDistrictService(DistrictMaster districtMaster) {
        try {
            logger.info("In addDistrictService");
            LocalDateTime now = LocalDateTime.now();
            districtMaster.setDate(now.toLocalDate());
            districtMaster.setTime(now.toLocalTime());

            // Validate and set State
            StateMaster state = stateService.getStateService(districtMaster.getState().getStateId());
            if (state != null) {
                districtMaster.setState(state);
            } else {
                logger.error("Invalid state ID");
                return false;
            }

            districtDAO.save(districtMaster);

            DistrictHistoryMaster history = new DistrictHistoryMaster();
            history.setDistrictId(districtMaster.getDistrictId());
            history.setDistrictName(districtMaster.getDistrictName());
            history.setDistrictDesc(districtMaster.getDistrictDesc());
            history.setStateId(districtMaster.getState().getStateId());
            history.setIsApproved(districtMaster.getIsApproved());
            history.setApprovedBy(districtMaster.getApprovedBy());
            history.setAddedBy(districtMaster.getAddedBy());
            history.setUpdatedBy(districtMaster.getUpdatedBy());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            districtHistoryDAO.save(history);
            logger.info("Out of addDistrictService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateDistrictService(DistrictMaster districtMaster) {
        try {
            logger.info("In updateDistrictService");

            DistrictMaster existing = districtDAO.findById(districtMaster.getDistrictId())
                    .orElseThrow(() -> new RuntimeException("District not found"));

            LocalDateTime now = LocalDateTime.now();

            DistrictHistoryMaster history = new DistrictHistoryMaster();
            history.setDistrictId(existing.getDistrictId());
            history.setDistrictName(existing.getDistrictName());
            history.setDistrictDesc(existing.getDistrictDesc());
            history.setStateId(districtMaster.getState().getStateId());
            history.setIsApproved(districtMaster.getIsApproved());
            history.setApprovedBy(districtMaster.getApprovedBy());
            history.setAddedBy(districtMaster.getAddedBy());
            history.setUpdatedBy(districtMaster.getUpdatedBy());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            existing.setDistrictName(districtMaster.getDistrictName());
            existing.setDistrictDesc(districtMaster.getDistrictDesc());
            existing.setIsApproved(districtMaster.getIsApproved());
            existing.setApprovedBy(districtMaster.getApprovedBy());
            existing.setAddedBy(districtMaster.getAddedBy());
            existing.setUpdatedBy(districtMaster.getUpdatedBy());

            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            districtDAO.save(existing);
            districtHistoryDAO.save(history);
            logger.info("Out of updateDistrictService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteDistrictService(DistrictMaster districtMaster) {
        try {
            logger.info("In deleteDistrictService");
            DistrictMaster existing = districtDAO.findById(districtMaster.getDistrictId())
                    .orElseThrow(() -> new RuntimeException("District not found"));

            DistrictHistoryMaster history = new DistrictHistoryMaster();
            history.setDistrictId(existing.getDistrictId());
            history.setDistrictName(existing.getDistrictName());
            history.setDistrictDesc(existing.getDistrictDesc());
            history.setStateId(districtMaster.getState().getStateId());
            history.setIsApproved(districtMaster.getIsApproved());
            history.setApprovedBy(districtMaster.getApprovedBy());
            history.setAddedBy(districtMaster.getAddedBy());
            history.setUpdatedBy(districtMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            districtDAO.delete(existing);
            districtHistoryDAO.save(history);
            logger.info("Out of deleteDistrictService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public DistrictMaster getDistrictService(Integer districtId) {
        try {
            logger.info("In getDistrictService");
            DistrictMaster districtMaster = districtDAO.findById(districtId).orElse(null);
            logger.info("Out of getDistrictService");
            return districtMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<DistrictMaster> getAllDistrictService() {
        List<DistrictMaster> allDistricts = new ArrayList<>();
        try {
            logger.info("In getAllDistrictService");
            allDistricts = (List<DistrictMaster>) districtDAO.findAll();
            logger.info("Out of getAllDistrictService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allDistricts;
    }
}
