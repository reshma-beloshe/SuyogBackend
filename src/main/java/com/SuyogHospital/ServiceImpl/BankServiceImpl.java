package com.SuyogHospital.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuyogHospital.Master.BankHistoryMaster;
import com.SuyogHospital.Master.BankMaster;
import com.SuyogHospital.Repository.BankDAO;
import com.SuyogHospital.Repository.BankHistoryDAO;
import com.SuyogHospital.Service.BankService;

@Service
public class BankServiceImpl implements BankService {

    private static final Logger logger = LogManager.getLogger(BankServiceImpl.class);

    @Autowired
    private BankDAO bankDAO;

    @Autowired
    private BankHistoryDAO bankHistoryDAO;

    @Override
    public Boolean addBankService(BankMaster bankMaster) {
        try {
            logger.info("In addBankService");

            LocalDateTime now = LocalDateTime.now();
            bankMaster.setDate(now.toLocalDate());
            bankMaster.setTime(now.toLocalTime());

            bankDAO.save(bankMaster);

            BankHistoryMaster history = new BankHistoryMaster();
            history.setBankId(bankMaster.getBankId());
            history.setBankName(bankMaster.getBankName());
            history.setDescription(bankMaster.getDescription());
            history.setStatus(bankMaster.getStatus());
            history.setIsApproved(bankMaster.getIsApproved());
            history.setApprovedBy(bankMaster.getApprovedBy());
            history.setAddedBy(bankMaster.getAddedBy());
            history.setUpdatedBy(bankMaster.getUpdatedBy());
            history.setCcPercent(bankMaster.getCcPercent());
            history.setBankLedger(bankMaster.getBankLedger());
            history.setAction("Create");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            bankHistoryDAO.save(history);
            logger.info("Out of addBankService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean updateBankService(BankMaster bankMaster) {
        try {
            logger.info("In updateBankService");

            BankMaster existing = bankDAO.findById(bankMaster.getBankId())
                    .orElseThrow(() -> new RuntimeException("Bank not found"));

            LocalDateTime now = LocalDateTime.now();

            BankHistoryMaster history = new BankHistoryMaster();
            history.setBankId(existing.getBankId());
            history.setBankName(existing.getBankName());
            history.setDescription(existing.getDescription());
            history.setStatus(existing.getStatus());
            history.setIsApproved(bankMaster.getIsApproved());
            history.setApprovedBy(bankMaster.getApprovedBy());
            history.setAddedBy(bankMaster.getAddedBy());
            history.setUpdatedBy(bankMaster.getUpdatedBy());
            history.setCcPercent(bankMaster.getCcPercent());
            history.setBankLedger(bankMaster.getBankLedger());
            history.setAction("Update");
            history.setDate(now.toLocalDate());
            history.setTime(now.toLocalTime());
            history.setTimestamp(now);

            existing.setBankName(bankMaster.getBankName());
            existing.setDescription(bankMaster.getDescription());
            existing.setStatus(bankMaster.getStatus());
            existing.setIsApproved(bankMaster.getIsApproved());
            existing.setApprovedBy(bankMaster.getApprovedBy());
            existing.setAddedBy(bankMaster.getAddedBy());
            existing.setUpdatedBy(bankMaster.getUpdatedBy());
            existing.setCcPercent(bankMaster.getCcPercent());
            existing.setBankLedger(bankMaster.getBankLedger());

            existing.setDate(now.toLocalDate());
            existing.setTime(now.toLocalTime());

            bankDAO.save(existing);
            bankHistoryDAO.save(history);
            logger.info("Out of updateBankService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteBankService(BankMaster bankMaster) {
        try {
            logger.info("In deleteBankService");

            BankMaster existing = bankDAO.findById(bankMaster.getBankId())
                    .orElseThrow(() -> new RuntimeException("Bank not found"));

            BankHistoryMaster history = new BankHistoryMaster();
            history.setBankId(existing.getBankId());
            history.setBankName(existing.getBankName());
            history.setDescription(existing.getDescription());
            history.setStatus(existing.getStatus());
            history.setIsApproved(bankMaster.getIsApproved());
            history.setApprovedBy(bankMaster.getApprovedBy());
            history.setAddedBy(bankMaster.getAddedBy());
            history.setUpdatedBy(bankMaster.getUpdatedBy());
            history.setCcPercent(bankMaster.getCcPercent());
            history.setBankLedger(bankMaster.getBankLedger());
            history.setAction("Delete");
            history.setTimestamp(LocalDateTime.now());

            bankDAO.delete(existing);
            bankHistoryDAO.save(history);
            logger.info("Out of deleteBankService");
            return true;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public BankMaster getBankService(Integer bankId) {
        try {
            logger.info("In getBankService");
            BankMaster bankMaster = bankDAO.findById(bankId).orElse(null);
            logger.info("Out of getBankService");
            return bankMaster;
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<BankMaster> getAllBankService() {
        List<BankMaster> allBanks = new ArrayList<>();
        try {
            logger.info("In getAllBankService");
            allBanks = (List<BankMaster>) bankDAO.findAll();
            logger.info("Out of getAllBankService");
        } catch (Exception e) {
            logger.error("IMS: " + e.getMessage(), e);
        }
        return allBanks;
    }
}
