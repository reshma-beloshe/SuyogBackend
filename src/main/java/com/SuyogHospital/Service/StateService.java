package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.StateMaster;

public interface StateService {

    public Boolean addStateService(StateMaster stateMaster);
    public Boolean updateStateService(StateMaster stateMaster);
    public Boolean deleteStateService(StateMaster stateMaster);
    public StateMaster getStateService(Integer stateId);
    public List<StateMaster> getAllStateService();
}
