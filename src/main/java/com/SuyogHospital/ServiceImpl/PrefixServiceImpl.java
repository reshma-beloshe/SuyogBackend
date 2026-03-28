package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.PrefixHistoryMaster;
import com.SuyogHospital.Master.PrefixMaster;
import com.SuyogHospital.Repository.PrefixDAO;
import com.SuyogHospital.Repository.PrefixHistoryDAO;
import com.SuyogHospital.Service.PrefixService;

@Service
public class PrefixServiceImpl implements PrefixService {

    private static final Logger logger = LogManager.getLogger(PrefixServiceImpl.class);

    @Autowired
    private PrefixDAO prefixDAO;

    @Autowired
    private PrefixHistoryDAO prefixHistoryDAO;

    @Override
    public Boolean addPrefix(PrefixMaster prefix) {
        try {
            logger.info("In addPrefix");

            LocalDateTime now = LocalDateTime.now();
            prefix.setDate(now.toLocalDate());
            prefix.setTime(now.toLocalTime());

            prefixDAO.save(prefix);

            PrefixHistoryMaster history = new PrefixHistoryMaster();
            history.setPrefixId(prefix.getPrefixId());
            history.setPrefixType(prefix.getPrefixType());
            history.setDescription(prefix.getDescription());
            history.setIsApproved(prefix.getIsApproved());
            history.setApprovedBy(prefix.getApprovedBy());
            history.setAddedBy(prefix.getAddedBy());
            history.setUpdatedBy(prefix.getUpdatedBy());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            prefixHistoryDAO.save(history);
            logger.info("Out of addPrefix");
            return true;
        } catch (Exception e) {
            logger.error("Error in addPrefix: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updatePrefix(PrefixMaster prefix) {
        try {
            logger.info("In updatePrefix");

            PrefixMaster existing = prefixDAO.findById(prefix.getPrefixId())
                    .orElseThrow(() -> new RuntimeException("Prefix not found"));

            LocalDateTime now = LocalDateTime.now();

            PrefixHistoryMaster history = new PrefixHistoryMaster();
            history.setPrefixId(existing.getPrefixId());
            history.setPrefixType(existing.getPrefixType());
            history.setDescription(existing.getDescription());
            history.setIsApproved(prefix.getIsApproved());
            history.setApprovedBy(prefix.getApprovedBy());
            history.setAddedBy(prefix.getAddedBy());
            history.setUpdatedBy(prefix.getUpdatedBy());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            existing.setPrefixType(prefix.getPrefixType());
            existing.setDescription(prefix.getDescription());
            existing.setIsApproved(prefix.getIsApproved());
            existing.setApprovedBy(prefix.getApprovedBy());
            existing.setAddedBy(prefix.getAddedBy());
            existing.setUpdatedBy(prefix.getUpdatedBy());
            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            prefixDAO.save(existing);
            prefixHistoryDAO.save(history);
            logger.info("Out of updatePrefix");
            return true;
        } catch (Exception e) {
            logger.error("Error in updatePrefix: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deletePrefix(PrefixMaster prefix) {
        try {
            logger.info("In deletePrefix");

            PrefixMaster existing = prefixDAO.findById(prefix.getPrefixId())
                    .orElseThrow(() -> new RuntimeException("Prefix not found"));

            PrefixHistoryMaster history = new PrefixHistoryMaster();
            history.setPrefixId(existing.getPrefixId());
            history.setPrefixType(existing.getPrefixType());
            history.setDescription(existing.getDescription());
            history.setIsApproved(existing.getIsApproved());
            history.setApprovedBy(existing.getApprovedBy());
            history.setAddedBy(existing.getAddedBy());
            history.setUpdatedBy(existing.getUpdatedBy());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            prefixDAO.delete(existing);
            prefixHistoryDAO.save(history);
            logger.info("Out of deletePrefix");
            return true;
        } catch (Exception e) {
            logger.error("Error in deletePrefix: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public PrefixMaster getPrefix(Integer prefixId) {
        try {
            logger.info("In getPrefix");
            PrefixMaster prefix = prefixDAO.findById(prefixId).orElse(null);
            logger.info("Out of getPrefix");
            return prefix;
        } catch (Exception e) {
            logger.error("Error in getPrefix: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<PrefixMaster> getAllPrefixes() {
        List<PrefixMaster> allPrefixes = new ArrayList<>();
        try {
            logger.info("In getAllPrefixes");
            allPrefixes = (List<PrefixMaster>) prefixDAO.findAll();
            logger.info("Out of getAllPrefixes");
        } catch (Exception e) {
            logger.error("Error in getAllPrefixes: " + e.getMessage(), e);
        }
        return allPrefixes;
    }
}
