package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.StateHistoryMaster;
import com.SuyogHospital.Master.StateMaster;
import com.SuyogHospital.Repository.StateDAO;
import com.SuyogHospital.Repository.StateHistoryDAO;
import com.SuyogHospital.Service.StateService;

@Service
public class StateServiceImpl implements StateService {

    private static final Logger logger = LogManager.getLogger(StateServiceImpl.class);

    @Autowired
    private StateDAO stateDAO;

    @Autowired
    private StateHistoryDAO stateHistoryDAO;
    
    @Override
    public Boolean addStateService(StateMaster stateMaster) {
        try {
            logger.info("In addStateService");
            LocalDateTime now = LocalDateTime.now();
            stateMaster.setDate(now.toLocalDate());
            stateMaster.setTime(now.toLocalTime());

            stateDAO.save(stateMaster);

            StateHistoryMaster history = new StateHistoryMaster();
            history.setStateId(stateMaster.getStateId());
            history.setStateName(stateMaster.getStateName());
            history.setStateDesc(stateMaster.getStateDesc());
            history.setIsApproved(stateMaster.getIsApproved());
            history.setApprovedBy(stateMaster.getApprovedBy());
            history.setAddedBy(stateMaster.getAddedBy());
            history.setUpdatedBy(stateMaster.getUpdatedBy());
            history.setCountryId(stateMaster.getCountry().getCountryId());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            stateHistoryDAO.save(history);
            logger.info("Out of addStateService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateStateService(StateMaster stateMaster) {
        try {
            logger.info("In updateStateService");

            StateMaster existing = stateDAO.findById(stateMaster.getStateId())
                    .orElseThrow(() -> new RuntimeException("State not found"));

            LocalDateTime now = LocalDateTime.now();

            StateHistoryMaster history = new StateHistoryMaster();
            history.setStateId(existing.getStateId());
            history.setStateName(existing.getStateName());
            history.setStateDesc(existing.getStateDesc());
            history.setCountryId(stateMaster.getCountry().getCountryId());
            history.setIsApproved(stateMaster.getIsApproved());
            history.setApprovedBy(stateMaster.getApprovedBy());
            history.setAddedBy(stateMaster.getAddedBy());
            history.setUpdatedBy(stateMaster.getUpdatedBy());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            existing.setStateName(stateMaster.getStateName());
            existing.setStateDesc(stateMaster.getStateDesc());
            existing.setIsApproved(stateMaster.getIsApproved());
            existing.setApprovedBy(stateMaster.getApprovedBy());
            existing.setAddedBy(stateMaster.getAddedBy());
            existing.setUpdatedBy(stateMaster.getUpdatedBy());

            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            stateDAO.save(existing);
            stateHistoryDAO.save(history);
            logger.info("Out of updateStateService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteStateService(StateMaster stateMaster) {
        try {
            logger.info("In deleteStateService");
            StateMaster existing = stateDAO.findById(stateMaster.getStateId())
                    .orElseThrow(() -> new RuntimeException("State not found"));

            StateHistoryMaster history = new StateHistoryMaster();
            history.setStateId(existing.getStateId());
            history.setStateName(existing.getStateName());
            history.setStateDesc(existing.getStateDesc());
            history.setCountryId(stateMaster.getCountry().getCountryId());
            history.setIsApproved(stateMaster.getIsApproved());
            history.setApprovedBy(stateMaster.getApprovedBy());
            history.setAddedBy(stateMaster.getAddedBy());
            history.setUpdatedBy(stateMaster.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            stateDAO.delete(existing);
            stateHistoryDAO.save(history);
            logger.info("Out of deleteStateService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public StateMaster getStateService(Integer stateId) {
        try {
            logger.info("In getStateService");
            StateMaster stateMaster = stateDAO.findById(stateId).orElse(null);
            logger.info("Out of getStateService");
            return stateMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<StateMaster> getAllStateService() {
        List<StateMaster> allStates = new ArrayList<>();
        try {
            logger.info("In getAllStateService");
            allStates = (List<StateMaster>) stateDAO.findAll();
            logger.info("Out of getAllStateService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allStates;
    }
}
