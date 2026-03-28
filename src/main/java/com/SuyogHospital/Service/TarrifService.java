package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.TarrifMaster;

public interface TarrifService {
    Boolean addTarrifService(TarrifMaster tarrifMaster);
    Boolean updateTarrifService(TarrifMaster tarrifMaster);
    Boolean deleteTarrifService(TarrifMaster tarrifMaster);
    TarrifMaster getTarrifService(Integer tarrifId);
    List<TarrifMaster> getAllTarrifService();
}
