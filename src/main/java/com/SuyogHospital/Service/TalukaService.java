package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.TalukaMaster;

public interface TalukaService {

    public Boolean addTalukaService(TalukaMaster TalukaMaster);
    public Boolean updateTalukaService(TalukaMaster TalukaMaster);
    public Boolean deleteTalukaService(TalukaMaster TalukaMaster);
    public TalukaMaster getTalukaService(Integer talId);
    public List<TalukaMaster> getAllTalukaService();
}
