package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.BankMaster;

public interface BankService {
    Boolean addBankService(BankMaster bankMaster);
    Boolean updateBankService(BankMaster bankMaster);
    Boolean deleteBankService(BankMaster bankMaster);
    BankMaster getBankService(Integer bankId);
    List<BankMaster> getAllBankService();
}
