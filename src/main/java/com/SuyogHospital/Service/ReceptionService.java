package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.ReceptionMaster;
import com.SuyogHospital.ResponseDTO.ReceptionDTO;

public interface ReceptionService {

    public Boolean addReceptionService(ReceptionDTO receptionMaster);
    public Boolean updateReceptionService(ReceptionDTO receptionMaster);
    public Boolean deleteReceptionService(ReceptionMaster receptionMaster);
    public ReceptionMaster getReceptionService(Integer receptionId);
    public List<ReceptionMaster> getAllReceptionService();
    
    List<ReceptionDTO> getAllReceptionsByMonthAndYear(int month, int year);
}
