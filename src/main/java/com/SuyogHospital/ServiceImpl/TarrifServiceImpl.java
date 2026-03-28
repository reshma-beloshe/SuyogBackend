package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.TarrifHistoryMaster;
import com.SuyogHospital.Master.TarrifMaster;
import com.SuyogHospital.Repository.TarrifDAO;
import com.SuyogHospital.Repository.TarrifHistoryDAO;
import com.SuyogHospital.Service.TarrifService;

@Service
public class TarrifServiceImpl implements TarrifService {

    private static final Logger logger = LogManager.getLogger(TarrifServiceImpl.class);

    @Autowired
    private TarrifDAO tarrifDAO;

    @Autowired
    private TarrifHistoryDAO tarrifHistoryDAO;

    @Override
    public Boolean addTarrifService(TarrifMaster tarrifMaster) {
        try {
            logger.info("In addTarrifService");

            LocalDateTime now = LocalDateTime.now();
            tarrifMaster.setDate(now.toLocalDate());
            tarrifMaster.setTime(now.toLocalTime());

            tarrifDAO.save(tarrifMaster);

            TarrifHistoryMaster history = createHistoryEntry(tarrifMaster, "Create", now);
            tarrifHistoryDAO.save(history);

            logger.info("Out of addTarrifService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateTarrifService(TarrifMaster tarrifMaster) {
        try {
            logger.info("In updateTarrifService");

            TarrifMaster existing = tarrifDAO.findById(tarrifMaster.getTarrifId())
                    .orElseThrow(() -> new RuntimeException("Tarrif not found"));

            LocalDateTime now = LocalDateTime.now();
            TarrifHistoryMaster history = createHistoryEntry(existing, "Update", now);

            // Updating existing entry
            existing.setTarrifType(tarrifMaster.getTarrifType());
            existing.setTarrifDescription(tarrifMaster.getTarrifDescription());
            existing.setStatus(tarrifMaster.getStatus());
            existing.setLinkWithService(tarrifMaster.getLinkWithService());
            existing.setPlusMinus(tarrifMaster.getPlusMinus());
            existing.setPercentage(tarrifMaster.getPercentage());
            existing.setIsApproved(tarrifMaster.getIsApproved());
            existing.setApprovedBy(tarrifMaster.getApprovedBy());
            existing.setAddedBy(tarrifMaster.getAddedBy());
            existing.setUpdatedBy(tarrifMaster.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            tarrifDAO.save(existing);
            tarrifHistoryDAO.save(history);

            logger.info("Out of updateTarrifService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteTarrifService(TarrifMaster tarrifMaster) {
        try {
            logger.info("In deleteTarrifService");

            TarrifMaster existing = tarrifDAO.findById(tarrifMaster.getTarrifId())
                    .orElseThrow(() -> new RuntimeException("Tarrif not found"));

            TarrifHistoryMaster history = createHistoryEntry(existing, "Delete", LocalDateTime.now());

            tarrifDAO.delete(existing);
            tarrifHistoryDAO.save(history);

            logger.info("Out of deleteTarrifService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public TarrifMaster getTarrifService(Integer tarrifId) {
        try {
            logger.info("In getTarrifService");
            TarrifMaster tarrifMaster = tarrifDAO.findById(tarrifId).orElse(null);
            logger.info("Out of getTarrifService");
            return tarrifMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<TarrifMaster> getAllTarrifService() {
        List<TarrifMaster> allTarrifs = new ArrayList<>();
        try {
            logger.info("In getAllTarrifService");
            allTarrifs = (List<TarrifMaster>) tarrifDAO.findAll();
            logger.info("Out of getAllTarrifService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allTarrifs;
    }

    private TarrifHistoryMaster createHistoryEntry(TarrifMaster tarrif, String action, LocalDateTime timestamp) {
        TarrifHistoryMaster history = new TarrifHistoryMaster();
        history.setTarrifId(tarrif.getTarrifId());
        history.setTarrifType(tarrif.getTarrifType());
        history.setTarrifDescription(tarrif.getTarrifDescription());
        history.setStatus(tarrif.getStatus());
        history.setLinkWithService(tarrif.getLinkWithService());
        history.setPlusMinus(tarrif.getPlusMinus());
        history.setPercentage(tarrif.getPercentage());
        history.setIsApproved(tarrif.getIsApproved());
        history.setApprovedBy(tarrif.getApprovedBy());
        history.setAddedBy(tarrif.getAddedBy());
        history.setUpdatedBy(tarrif.getUpdatedBy());
        history.setAction(action);
        history.setDate(timestamp.toLocalDate());
        history.setTime(timestamp.toLocalTime());
        history.setTimestamp(timestamp);

        return history;
    }
}
